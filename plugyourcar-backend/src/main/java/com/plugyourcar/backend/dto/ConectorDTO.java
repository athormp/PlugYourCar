package com.plugyourcar.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConectorDTO {

		@JsonProperty("ID")
		private int id;
		@JsonProperty("ConnectionTypeID")
		private int tipoConector;
		@JsonProperty("Amps")
		private int amperaje;
		@JsonProperty("Voltage")
		private int voltaje;
		@JsonProperty("PowerKW")
		private int potencia;
		@JsonProperty("LevelID")
		private int tipoCargador;
		@JsonProperty("CurrentTypeID")
		private int tipoCorriente;
		@JsonProperty("Quantity")
		private int quantity;
		
		public int getId() {
			return id;
		}
		
		public void setId(int id) {
			this.id = id;
		}
		
		public int getTipoConector() {
			return tipoConector;
		}
		
		public void setTipoConector(int tipoConector) {
			this.tipoConector = tipoConector;
		}
		
		public int getAmperaje() {
			return amperaje;
		}
		
		public void setAmperaje(int amperaje) {
			this.amperaje = amperaje;
		}
		
		public int getVoltaje() {
			return voltaje;
		}
		
		public void setVoltaje(int voltaje) {
			this.voltaje = voltaje;
		}
		
		public int getPotencia() {
			return potencia;
		}
		
		public void setPotencia(int potencia) {
			this.potencia = potencia;
		}
		
		public int getTipoCargador() {
			return tipoCargador;
		}
		
		public void setTipoCargador(int tipoCargador) {
			this.tipoCargador = tipoCargador;
		}
		
		public int getTipoCorriente() {
			return tipoCorriente;
		}
		
		public void setTipoCorriente(int tipoCorriente) {
			this.tipoCorriente = tipoCorriente;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

}
