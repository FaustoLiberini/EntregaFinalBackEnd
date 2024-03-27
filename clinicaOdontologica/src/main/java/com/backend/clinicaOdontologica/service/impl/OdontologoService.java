package com.backend.clinicaOdontologica.service.impl;


import com.backend.clinicaOdontologica.dto.entry.OdontologoEntryDto;
import com.backend.clinicaOdontologica.dto.exit.OdontologoExitDto;
import com.backend.clinicaOdontologica.entity.Odontologo;
import com.backend.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.backend.clinicaOdontologica.repository.OdontologoRepository;
import com.backend.clinicaOdontologica.service.IOdontologoService;
import com.backend.clinicaOdontologica.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {

    private final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);
    private  ModelMapper modelMapper;
    private OdontologoRepository odontologoRepository;

    public OdontologoService(OdontologoRepository odontologoRepository, ModelMapper modelMapper) {
        this.odontologoRepository = odontologoRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public OdontologoExitDto registrarOdontologo(OdontologoEntryDto odontologo) {

        Odontologo odontologoEntiedad = modelMapper.map(odontologo, Odontologo.class);
        Odontologo odontologoConId = odontologoRepository.save(odontologoEntiedad);
        OdontologoExitDto odontologoExitDto = modelMapper.map(odontologoConId, OdontologoExitDto.class);
        LOGGER.info("OdontologoExitDto: {}", JsonPrinter.toString(odontologoExitDto));

        return odontologoExitDto;
    }

    @Override
    public List<OdontologoExitDto> listarOdontologo() {

        List<OdontologoExitDto> listaOdontologosExitDto = odontologoRepository.findAll()
                .stream()
                .map(odontologo -> modelMapper.map(odontologo, OdontologoExitDto.class))
                .toList();

        LOGGER.info("Listado de todos los odontologos: {}", JsonPrinter.toString(listaOdontologosExitDto));
        return listaOdontologosExitDto;
    }

    @Override
    public OdontologoExitDto buscarOdontologoPorId(Long id) {

        Odontologo odontologoBuscado = odontologoRepository.findById(id).orElse(null);
        OdontologoExitDto odontologoEncontrado = null;

        if (odontologoBuscado != null) {
            odontologoEncontrado = modelMapper.map(odontologoBuscado, OdontologoExitDto.class);
            LOGGER.info("Odontologo encontrado: {}", JsonPrinter.toString(odontologoEncontrado));

        } else LOGGER.error("No se encontro un odontologo con ese ID en la base de datos");

        return odontologoEncontrado;
    }

    @Override
    public void eliminarOrdontologoPorId(Long id) throws ResourceNotFoundException {

        if (buscarOdontologoPorId(id) != null) {
            odontologoRepository.deleteById(id);
            LOGGER.warn("Se elimino el odontologo con id {}", id);
        } else {
            throw new ResourceNotFoundException("No existe un registro de Odontologo con id: " + id);
        }

    }

    @Override
    public OdontologoExitDto modificarOdontologo(OdontologoEntryDto odontologoEntryDto, Long id) {
        Odontologo odontologoActualizado = odontologoRepository.findById(id).orElse(null);
        OdontologoExitDto odontologoExitDto = null;

        if (odontologoActualizado != null) {

            odontologoActualizado.setNombre(odontologoEntryDto.getNombre());
            odontologoActualizado.setApellido(odontologoEntryDto.getApellido());

            odontologoActualizado = odontologoRepository.save(odontologoActualizado);

            odontologoExitDto = modelMapper.map(odontologoActualizado, OdontologoExitDto.class);

            LOGGER.warn("Odontologo actualizado: {}", odontologoExitDto);
        } else {
            LOGGER.error("Error al actualizar el odontologo: no se encuentra en la BD");
        }
        return odontologoExitDto;
    }


}
