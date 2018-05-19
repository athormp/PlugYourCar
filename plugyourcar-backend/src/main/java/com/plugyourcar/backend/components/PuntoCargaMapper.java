package com.plugyourcar.backend.components;

import org.springframework.stereotype.Component;

@Component
public class PuntoCargaMapper {
	
//	@Autowired
//	private ModelMapper modelMapper;
//	
//	@Autowired
//	private OperadorRepository operadorRepository;
//	
//	@Autowired
//	private TipoUsoRepository tipoUsoRepository;
//	
//	@Autowired
//	private TipoCargadorRepository tipoCargadorRepository;
//	
//	@Autowired
//	private TipoConectorRepository tipoConectorRepository;
//	
//	@Autowired
//	private TipoCorrienteRepository tipoCorrienteRepository;
//	
//	public PuntoCargaMapper() {
//		 ModelMapper modelMapper = new ModelMapper();
//		 modelMapper.addMappings(ToPuntoCargaMapping);
//	}
//	
////	public PuntoCargaMapper() {
////		 modelMapper.addMappings(new PropertyMap<PuntoCargaDTO, PuntoCarga>() {
////			 protected void configure() {
////				 map().setId(source.getId());
////				 map().setUuid(source.getUuid());
////				 map().setReferenciaOperador(source.getReferenciaOperador());
////				 map().setCosteUso(source.getCosteUso());
////				 map().setNumeroPuntos(source.getNumeroPuntos());
////				 map().getLocalizacion().setId(source.getLocalizacioDTO().getId());
////				 map().getLocalizacion().setNombre(source.getLocalizacioDTO().getNombre());
////				 map().getLocalizacion().setDireccion(source.getLocalizacioDTO().getDireccion());
////				 map().getLocalizacion().setLocalidad(source.getLocalizacioDTO().getLocalidad());
////				 map().getLocalizacion().setProvincia(source.getLocalizacioDTO().getProvincia());
////				 map().getLocalizacion().setTelefonoContacto(source.getLocalizacioDTO().getTelefonoContacto());
////				 map().getLocalizacion().setLatitud(source.getLocalizacioDTO().getLatitud());
////				 map().getLocalizacion().setLongitud(source.getLocalizacioDTO().getLongitud());
////				 Operador operador = operadorRepository.findOne(source.getOperador());
////				 map().setOperador(operador);
////				 TipoUso tipoUso = tipoUsoRepository.findOne(source.getTipoUso());
////				 map().setTipoUso(tipoUso);
////				 List<EquipoSuministro> equiposSuministro = new ArrayList<EquipoSuministro>();
////				 for (ConectorDTO conectorDTO : source.getConectoresDTO()) {
////					 EquipoSuministro equipoSuministro = new EquipoSuministro();
////					 equipoSuministro.setId(conectorDTO.getId());
////					 equipoSuministro.setAmperaje(conectorDTO.getAmperaje());
////					 equipoSuministro.setPotencia(conectorDTO.getPotencia());
////					 equipoSuministro.setVoltaje(conectorDTO.getVoltaje());
////					 TipoCargador tipoCargador = tipoCargadorRepository.findOne(conectorDTO.getTipoCargador());
////					 TipoConector tipoConector = tipoConectorRepository.findOne(conectorDTO.getTipoConector());
////					 TipoCorriente tipoCorriente = tipoCorrienteRepository.findOne(conectorDTO.getTipoCorriente());
////					 equipoSuministro.setTipoCargador(tipoCargador);
////					 equipoSuministro.setTipoConector(tipoConector);
////					 equipoSuministro.setTipoCorriente(tipoCorriente);
////					 List<Conector> conectores = new ArrayList<Conector>();
////					 for (int i = 0; i < conectorDTO.getQuantity(); i++) {
////						 Conector conector = new Conector();
////						 conector.setIdReferencia(i);
////						 conectores.add(conector);
////					 }
////					 equipoSuministro.setConectores(conectores);
////					 equiposSuministro.add(equipoSuministro);
////				 }
////				 map().setEquiposSuministro(equiposSuministro);
////			 }
////		 });
////	}
//	
//	 /** Mapeo de PuntoCargaDTO a PuntoCarga **/
//	 public PuntoCarga toPuntoCarga(PuntoCargaDTO puntoCargaDTO) {
//		 return modelMapper.map(puntoCargaDTO, PuntoCarga.class);
//	 }
//	 
////	 /** Mapeo desde una lista de PuntosCargaDTO a otra de PuntosCarga **/
////	 public List<PuntoCarga> toPuntoCargaList(List<PuntoCargaDTO> puntosCargaDTO) {
////		 Type tipoLista =  new TypeToken<List<PuntoCarga>>() {}.getType();
////		 List<PuntoCargaDTO> puntosCargaDTO = modelMapper.map(puntosCargaDTO, listType);
////		 return customerDTOs;
////	 }
//
//	 private PropertyMap<PuntoCargaDTO, PuntoCarga> ToPuntoCargaMapping = 
//		new PropertyMap<PuntoCargaDTO, PuntoCarga>() {
//		 protected void configure() {
//			 map().setId(source.getId());
//			 map().setUuid(source.getUuid());
//			 map().setReferenciaOperador(source.getReferenciaOperador());
//			 map().setCosteUso(source.getCosteUso());
//			 map().setNumeroPuntos(source.getNumeroPuntos());
//			 map().getLocalizacion().setId(source.getLocalizacioDTO().getId());
//			 map().getLocalizacion().setNombre(source.getLocalizacioDTO().getNombre());
//			 map().getLocalizacion().setDireccion(source.getLocalizacioDTO().getDireccion());
//			 map().getLocalizacion().setLocalidad(source.getLocalizacioDTO().getLocalidad());
//			 map().getLocalizacion().setProvincia(source.getLocalizacioDTO().getProvincia());
//			 map().getLocalizacion().setTelefonoContacto(source.getLocalizacioDTO().getTelefonoContacto());
//			 map().getLocalizacion().setLatitud(source.getLocalizacioDTO().getLatitud());
//			 map().getLocalizacion().setLongitud(source.getLocalizacioDTO().getLongitud());
//			 Operador operador = operadorRepository.findOne(source.getOperador());
//			 map().setOperador(operador);
//			 TipoUso tipoUso = tipoUsoRepository.findOne(source.getTipoUso());
//			 map().setTipoUso(tipoUso);
//			 List<EquipoSuministro> equiposSuministro = new ArrayList<EquipoSuministro>();
//			 for (ConectorDTO conectorDTO : source.getConectoresDTO()) {
//				 EquipoSuministro equipoSuministro = new EquipoSuministro();
//				 equipoSuministro.setId(conectorDTO.getId());
//				 equipoSuministro.setAmperaje(conectorDTO.getAmperaje());
//				 equipoSuministro.setPotencia(conectorDTO.getPotencia());
//				 equipoSuministro.setVoltaje(conectorDTO.getVoltaje());
//				 TipoCargador tipoCargador = tipoCargadorRepository.findOne(conectorDTO.getTipoCargador());
//				 TipoConector tipoConector = tipoConectorRepository.findOne(conectorDTO.getTipoConector());
//				 TipoCorriente tipoCorriente = tipoCorrienteRepository.findOne(conectorDTO.getTipoCorriente());
//				 equipoSuministro.setTipoCargador(tipoCargador);
//				 equipoSuministro.setTipoConector(tipoConector);
//				 equipoSuministro.setTipoCorriente(tipoCorriente);
//				 List<Conector> conectores = new ArrayList<Conector>();
//				 for (int i = 0; i < conectorDTO.getQuantity(); i++) {
//					 Conector conector = new Conector();
//					 conector.setIdReferencia(i);
//					 conectores.add(conector);
//				 }
//				 equipoSuministro.setConectores(conectores);
//				 equiposSuministro.add(equipoSuministro);
//			 }
//			 map().setEquiposSuministro(equiposSuministro);
//		  }
//	 	};
//	 	
//
////	static class ToCustomerDTOMapping extends PropertyMap<CustomerSvcDTO, CustomerDTO> {
////		/** Define mapping configure PropertyMap <Source, Destination> **/
////		@Override
////		protected void configure() {
////			map().setCustNum(source.getCustomerNum());
////			map().setName(source.getTrustInvestorName());
////			skip().setStreet(null);
////			skip().setCity(null);
////			skip().setCountry(null);
////			skip().setDob(source.getDateOfBirth());
////			map().setReturnCode(source.getReturnCode());
////		}
////	}
}
