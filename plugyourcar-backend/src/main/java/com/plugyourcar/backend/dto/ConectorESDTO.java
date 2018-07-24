package com.plugyourcar.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConectorESDTO {

		@JsonProperty
		private Integer id;
		@JsonProperty
		private Integer tipoConector;
		@JsonProperty
		private Integer amperaje;
		@JsonProperty
		private Integer voltaje;
		@JsonProperty
		private Double potencia;
		@JsonProperty
		private Integer tipoCargador;
		@JsonProperty
		private Integer tipoCorriente;
		@JsonProperty
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
