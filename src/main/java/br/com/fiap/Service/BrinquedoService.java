package br.com.fiap.Service;


import br.com.fiap.Model.Brinquedo;
import br.com.fiap.Repository.BrinquedoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrinquedoService {

    private final BrinquedoRepository brinquedoRepository;

    @Autowired
    public BrinquedoService(BrinquedoRepository brinquedoRepository) {
        this.brinquedoRepository = brinquedoRepository;
    }

    public Brinquedo saveBrinquedo(Brinquedo brinquedo) {
        return brinquedoRepository.save(brinquedo);
    }

    public List<Brinquedo> getAllBrinquedos() {
        return brinquedoRepository.findAll();
    }

    public Optional<Brinquedo> getBrinquedoById(Long id) {
        return brinquedoRepository.findById(id);
    }

    public Brinquedo updateBrinquedo(Long id, Brinquedo brinquedo) {
        if (brinquedoRepository.existsById(id)) {
            brinquedo.setId(id);
            return brinquedoRepository.save(brinquedo);
        }
        return null; // Se o brinquedo não existir, retorna null ou pode lançar uma exceção
    }

    public boolean deleteBrinquedo(Long id) {
        if (brinquedoRepository.existsById(id)) {
            brinquedoRepository.deleteById(id);
            return true;
        }
        return false; // Retorna falso se o brinquedo não existir
    }
}

