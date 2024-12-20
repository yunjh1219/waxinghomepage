package edu.du._waxing_home.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;  // PatientRepository를 사용하여 환자 정보 저장

    // 특정 환자 조회
    public Customer getPatientById(Long patientId) {
        return customerRepository.findById(patientId).orElse(null);
    }

    // 환자 저장
    public void savePatient(Customer customer) {
        customerRepository.save(customer);
    }

    // 페이징 처리된 모든 환자 조회
    public Page<Customer> getPatients(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    // 검색 조건에 맞는 환자 조회 (페이징 처리)
    public Page<Customer> searchPatients(String search, String searchType, Pageable pageable) {
        switch (searchType) {
            case "id":
                try {
                    Long id = Long.parseLong(search);  // String을 Long으로 변환
                    return customerRepository.findById(id, pageable);
                } catch (NumberFormatException e) {
                    return Page.empty();  // 숫자가 아니면 빈 페이지 반환
                }
            case "name":
                return customerRepository.findByNameContaining(search, pageable);
            case "phonenumber":
                return customerRepository.findByPhonenumberContaining(search, pageable);
            default:
                return Page.empty();  // 검색 타입이 잘못되었을 경우 빈 페이지 반환
        }
    }
}