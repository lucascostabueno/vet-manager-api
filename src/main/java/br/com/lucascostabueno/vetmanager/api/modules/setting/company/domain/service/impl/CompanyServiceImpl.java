package br.com.lucascostabueno.vetmanager.api.modules.setting.company.domain.service.impl;

import br.com.lucascostabueno.vetmanager.api.modules.setting.company.application.dto.CompanyCreateRequest;
import br.com.lucascostabueno.vetmanager.api.modules.setting.company.application.dto.CompanyResponse;
import br.com.lucascostabueno.vetmanager.api.modules.setting.company.application.dto.CompanySearchFilter;
import br.com.lucascostabueno.vetmanager.api.modules.setting.company.application.dto.CompanyUpdateRequest;
import br.com.lucascostabueno.vetmanager.api.modules.setting.company.application.mapper.CompanyMapper;
import br.com.lucascostabueno.vetmanager.api.modules.setting.company.domain.model.Company;
import br.com.lucascostabueno.vetmanager.api.modules.setting.company.domain.model.CompanyAddress;
import br.com.lucascostabueno.vetmanager.api.modules.setting.company.domain.model.CompanyContact;
import br.com.lucascostabueno.vetmanager.api.modules.setting.company.domain.repository.CompanyAddressRepository;
import br.com.lucascostabueno.vetmanager.api.modules.setting.company.domain.repository.CompanyContactRepository;
import br.com.lucascostabueno.vetmanager.api.modules.setting.company.domain.repository.CompanyRepository;
import br.com.lucascostabueno.vetmanager.api.modules.setting.company.domain.service.CompanyService;
import br.com.lucascostabueno.vetmanager.api.modules.setting.company.infrastructure.persistence.specification.CompanySpecs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

  private final CompanyRepository companyRepository;
  private final CompanyContactRepository contactRepository;
  private final CompanyAddressRepository addressRepository;
  private final CompanyMapper mapper;

  @Override
  @Transactional(readOnly = true)
  public CompanyResponse findById(UUID id) {
    Company company = companyRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Company not found."));

    CompanyContact contact = contactRepository.findByCompanyId(id).orElse(null);
    CompanyAddress address = addressRepository.findByCompanyId(id).orElse(null);

    return mapper.toResponse(company, contact, address);
  }

  @Override
  @Transactional
  public CompanyResponse create(CompanyCreateRequest request) {
    Company company = mapper.toEntity(request);
    Company savedCompany = companyRepository.save(company);

    CompanyContact contact = mapper.toContactEntity(request.contact());
    contact.setCompany(savedCompany);
    CompanyContact savedContact = contactRepository.save(contact);

    CompanyAddress address = mapper.toAddressEntity(request.address());
    address.setCompany(savedCompany);
    CompanyAddress savedAddress = addressRepository.save(address);

    return mapper.toResponse(savedCompany, savedContact, savedAddress);
  }

  @Override
  @Transactional
  public CompanyResponse update(UUID id, CompanyUpdateRequest request) {
    Company company = companyRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Company not found."));

    mapper.updateEntity(request, company);
    Company updatedCompany = companyRepository.save(company);

    CompanyContact contact = contactRepository.findByCompanyId(id).orElseGet(() -> {
      CompanyContact newContact = new CompanyContact();
      newContact.setCompany(updatedCompany);
      return newContact;
    });
    mapper.updateContactEntity(request.contact(), contact);
    CompanyContact updatedContact = contactRepository.save(contact);

    CompanyAddress address = addressRepository.findByCompanyId(id).orElseGet(() -> {
      CompanyAddress newAddress = new CompanyAddress();
      newAddress.setCompany(updatedCompany);
      return newAddress;
    });
    mapper.updateAddressEntity(request.address(), address);
    CompanyAddress updatedAddress = addressRepository.save(address);

    return mapper.toResponse(updatedCompany, updatedContact, updatedAddress);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<CompanyResponse> search(CompanySearchFilter filter, Pageable pageable) {
    return companyRepository.findAll(CompanySpecs.byFilter(filter), pageable).map(company -> {
      CompanyContact contact = contactRepository.findByCompanyId(company.getId()).orElse(null);
      CompanyAddress address = addressRepository.findByCompanyId(company.getId()).orElse(null);
      return mapper.toResponse(company, contact, address);
    });
  }

  @Override
  @Transactional
  public void delete(UUID id) {
    if (!companyRepository.existsById(id))
      throw new RuntimeException("Invalid ID.");

    contactRepository.findByCompanyId(id).ifPresent(contactRepository::delete);
    addressRepository.findByCompanyId(id).ifPresent(addressRepository::delete);

    companyRepository.deleteById(id);
  }
}
