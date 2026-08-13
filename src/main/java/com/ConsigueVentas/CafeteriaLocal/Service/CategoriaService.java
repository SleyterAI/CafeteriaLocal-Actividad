package com.ConsigueVentas.CafeteriaLocal.Service;

import com.ConsigueVentas.CafeteriaLocal.Entity.Categoria;
import com.ConsigueVentas.CafeteriaLocal.Repository.CategoriaRepository;
import com.ConsigueVentas.CafeteriaLocal.Service.Interface.ICategoriaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService implements ICategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Categoria createCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public List<Categoria> getAllCategoria() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria getCategoriaoById(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("La categoria no existe")
                );
    }

    @Override
    public Categoria updateCategoria(Long id, Categoria categoria) {
        Categoria categoriaUpdate = categoriaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("La categoria no existe")
                );
        categoriaUpdate.setNombre(categoria.getNombre());
        return categoriaRepository.save(categoriaUpdate);
    }

    @Override
    public void deleteCategoria(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException("La categoria no existe");
        }
        categoriaRepository.deleteById(id);
    }
}
