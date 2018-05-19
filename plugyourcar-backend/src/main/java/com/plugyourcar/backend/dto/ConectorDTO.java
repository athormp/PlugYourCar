package com.plugyourcar.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConectorDTO {

		@JsonProperty("ID")
		private Integer id;
		@JsonProperty("ConnectionTypeID")
		private Integer tipoConector;
		@JsonProperty("Amps")
		private Integer amperaje;
		@JsonProperty("Voltage")
		private Integer voltaje;
		@JsonProperty("PowerKW")
		private Double potencia;
		@JsonProperty("LevelID")
		private Integer tipoCargador;
		@JsonProperty("CurrentTypeID")
		private Integer tipoCorriente;
		@JsonProperty("Quantity")
		private Integer quantity;
		
		public Integer getId() {
			return id;
		}
		
		public void setId(Integer id) {
			this.id = id;
		}
		
		public Integer getTipoConector() {
			return tipoConector;
		}
		
		public void setTipoConector(Integer tipoConector) {
			this.tipoConector = tipoConector;
		}
		
		public Integer getAmperaje() {
			return amperaje;
		}
		
		public void setAmperaje(Integer amperaje) {
			this.amperaje = amperaje;
		}
		
		public Integer getVoltaje() {
			return voltaje;
		}
		
		public void setVoltaje(Integer voltaje) {
			this.voltaje = voltaje;
		}
		
		public Double getPotencia() {
			return potencia;
		}
		
		public void setPotencia(Double potencia) {
			this.potencia = potencia;
		}
		
		public Integer getTipoCargador() {
			return tipoCargador;
		}
		
		public void setTipoCargador(Integer tipoCargador) {
			this.tipoCargador = tipoCargador;
		}
		
		public Integer getTipoCorriente() {
			return tipoCorriente;
		}
		
		public void setTipoCorriente(Integer tipoCorriente) {
			this.tipoCorriente = tipoCorriente;
		}
		
		public Integer getQuantity() {
			return quantity;
		}
		
		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}

}
