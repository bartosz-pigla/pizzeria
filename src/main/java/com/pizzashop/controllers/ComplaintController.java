package com.pizzashop.controllers;

import com.pizzashop.models.Complaint;
import com.pizzashop.repositories.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Kontroler zawierający REST servicy wykonujace operacje CRUD na encji Reklamacje
 * Created by Bartosz Pigla on 10/12/2016.
 */
@RestController
@RequestMapping("/complaint")
public class ComplaintController {
    /**
     * repozytorium reklamacji z którego korzystają webservicy
     */
    @Autowired
    ComplaintRepository complaintRepository;

    /**
     * Metoda pobiera z bazy danych reklamacje.
     * @param pageSize liczba elementów do pobrania z bazy danych
     * @param pageNumber numer strony
     * @return lista reklamacji
     */
    @RequestMapping("/read")
    public List<Complaint> read(
            @RequestParam(value = "pageSize", required = true) Integer pageSize,
            @RequestParam(value = "pageNumber", required = true) Integer pageNumber) {

        return complaintRepository
                .findAll(new PageRequest((pageNumber == null) ? 0 : pageNumber - 1, pageSize))
                .getContent();
    }

    /**
     * metoda pobierająca liczbę wszystkich reklamacji w bazie danych
     * @return liczba wszystkich reklamacji w bazie danych
     */

    @RequestMapping("/count")
    public Long count() {
        return complaintRepository.count();
    }
}
