package com.plugyourcar.backend.components;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.plugyourcar.backend.components.PuntoCargaMapper.ToPuntoCargaMapping;
import com.plugyourcar.backend.dto.ConectorDTO;
import com.plugyourcar.backend.dto.LocalizacionDTO;
import com.plugyourcar.backend.dto.PuntoCargaDTO;
import com.plugyourcar.backend.model.Conector;
import com.plugyourcar.backend.model.EquipoSuministro;
import com.plugyourcar.backend.model.Operador;
import com.plugyourcar.backend.model.PuntoCarga;
import com.plugyourcar.backend.model.TipoCargador;
import com.plugyourcar.backend.model.TipoConector;
import com.plugyourcar.backend.model.TipoCorriente;
import com.plugyourcar.backend.model.TipoUso;
import com.plugyourcar.backend.repositories.ConectorRepository;
import com.plugyourcar.backend.repositories.OperadorRepository;
import com.plugyourcar.backend.repositories.TipoCargadorRepository;
import com.plugyourcar.backend.repositories.TipoConectorRepository;
import com.plugyourcar.backend.repositories.TipoCorrienteRepository;
import com.plugyourcar.backend.repositories.TipoUsoRepository;

@Component
public class PuntoCargaMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private OperadorRepository operadorRepository;
	
	@Autowired
	private TipoUsoRepository tipoUsoRepository;
	
	@Autowired
	private TipoCargadorRepository tipoCargadorRepository;
	
	@Autowired
	private TipoConectorRepository tipoConectorRepository;
	
	@Autowired
	private TipoCorrienteRepository tipoCorrienteRepository;
	
	@Autowired
	private ConectorRepository conectorRepository;
	
	public PuntoCargaMapper() {
		 modelMapper.addMappings(new ToPuntoCargaMapping());
	 }
//	 /** Mapping from CustomerSvcDTo to CustomerDTO **/
//	 public CustomerDTO toCustomerDTO(CustomerSvcDTO customerSvcDTO) {
//		 return modelMapper.map(customerSvcDTO, CustomerDTO.class);
//	 }
//	 /** Mapping from CustomerDTO to CustomerSvcDTO **/
//	 public CustomerSvcDTO toCustomerSvcDTO(CustomerDTO customerDTO) {
//		 return modelMapper.map(customerDTO, CustomerSvcDTO.class);
//	 }
//	 /** Mapping from CustomerSvcDTO List to CustomerDTO List **/
//	 public List<CustomerDTO> toCustomerDTOList(List<CustomerSvcDTO> customerSvcDTOList) {
//		 Type listType =  new TypeToken<List<CustomerDTO>>() {}.getType();
//		 List<CustomerDTO> customerDTOs = modelMapper.map(customerSvcDTOList, listType);
//		 return customerDTOs;
//	 }

	 static class ToPuntoCargaMapping extends PropertyMap<PuntoCargaDTO, PuntoCarga> {
		 /** Definir mapping configure PropertyMap <Source, Destination> **/
		 @Override
		 protected void configure() {
			 map().setId(source.getId());
			 map().setUuid(source.getUuid());
			 map().setReferenciaOperador(source.getReferenciaOperador());
			 map().setCosteUso(source.getCosteUso());
			 map().setNumeroPuntos(source.getNumeroPuntos());
			 LocalizacionDTO localizacionDTO = source.getLocalizacioDTO();
			 map().setLocalizacion(source.getLocalizacionDTO());
			 Operador operador = operadorRepository.findOne(source.getOperador());
			 map().setOperador(operador);
			 TipoUso tipoUso = tipoUsoRepository.findOne(source.getTipoUso());
			 map().setTipoUso(tipoUso);
			 List<EquipoSuministro> equiposSuministro = new List<EquiposSuministro>();
			 for (ConectorDTO conectorDTO : source.getConectoresDTO()) {
				 EquipoSuministro equipoSuministro = new EquipoSuministro();
				 equipoSuministro.setId(conectorDTO.getId());
				 equipoSuministro.setAmperaje(conectorDTO.getAmperaje());
				 equipoSuministro.setPotencia(conectorDTO.getPotencia());
				 equipoSuministro.setVoltaje(conectorDTO.getVoltaje());
				 TipoCargador tipoCargador = tipoCargadorRepository.findOne(conectorDTO.getTipoCargador());
				 TipoConector tipoConector = tipoConectorRepository.findOne(conectorDTO.getTipoConector());
				 TipoCorriente tipoCorriente = tipoCorrienteRepository.findOne(conectorDTO.getTipoCorriente());
				 equipoSuministro.setTipoCargador(tipoCargador);
				 equipoSuministro.setTipoConector(tipoConector);
				 equipoSuministro.setTipoCorriente(tipoCorriente);
				 List<Conector> conectores = new List<Conector>();
				 for (i = 0; i < conectorDTO.getQuantity(); i++) {
					 Conector conector = new Conector();
					 conector.setIdReferencia(i);
					 conectores.add(conector);
				 }
				 equipoSuministro.setConectores(conectores);
				 equiposSuministro.add(equipoSuministro);
			 }
			 map().setEquiposSuministro(equiposSuministro);
		 }
	 }

//	static class ToCustomerDTOMapping extends PropertyMap<CustomerSvcDTO, CustomerDTO> {
//		/** Define mapping configure PropertyMap <Source, Destination> **/
//		@Override
//		protected void configure() {
//			map().setCustNum(source.getCustomerNum());
//			map().setName(source.getTrustInvestorName());
//			skip().setStreet(null);
//			skip().setCity(null);
//			skip().setCountry(null);
//			skip().setDob(source.getDateOfBirth());
//			map().setReturnCode(source.getReturnCode());
//		}
//	}
}
