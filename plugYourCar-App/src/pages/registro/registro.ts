import { RegistroService } from './registro.service';
import { Usuario } from '../../model/usuario.model';
import { DniValidator } from '../../validators/DniValidator';
import { PasswordValidator } from '../../validators/PasswordValidator';
import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { LoginPage } from '../login/login';

@Component({
  selector: 'registro',
  templateUrl: 'registro.html'
})
export class RegistroPage {

  registroForm: FormGroup;
  usuario: Usuario;
  mensajes_validacion;
  grupo_passwords;
  errorResponse: boolean;
  successResponse: boolean;
  mensajeResultado: string;

  constructor(public navCtrl: NavController,
    public navParams: NavParams,
    private formBuilder: FormBuilder, private registroService: RegistroService) {
    this.grupo_passwords = new FormGroup({
      password: new FormControl(
        '', Validators.compose([
          Validators.required,
          Validators.pattern(/^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/)
        ])),
      password2: new FormControl(
        '', Validators.required
      )
    }, (formGroup: FormGroup) => {
      return PasswordValidator.coinciden(formGroup);
    });

    this.registroForm = this.formBuilder.group({
      dni: [
        '', Validators.compose([
          Validators.required,
          Validators.minLength(9),
          DniValidator.validar])
      ],
      nombre: ['', Validators.required],
      apellidos: ['', Validators.required],
      email: ['', Validators.compose([
        Validators.pattern(/^[a-zA-Z0-9._]+[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/),
        Validators.required
      ])
      ],
      telefonoContacto: ['', Validators.required],
      marcaVehiculo: [''],
      passwords_coincidentes: this.grupo_passwords
    });

    this.mensajes_validacion = {
      'dni': [
        { type: 'required', message: 'Campo obligatorio' },
        { type: 'minlength', message: 'El DNI debe contener 9 caracteres' },
        { type: 'validar', message: 'El DNI introducido no es correcto' },
      ],
      'nombre': [
        { type: 'required', message: 'Campo obligatorio' }
      ],
      'apellidos': [
        { type: 'required', message: 'Campo obligatorio' }
      ],
      'email': [
        { type: 'required', message: 'Campo obligatorio' },
        { type: 'pattern', message: 'El email introducido no es válido' }
      ],
      'telefonoContacto': [
        { type: 'required', message: 'Campo obligatorio' }
      ],
      'marcaVehiculo': [
        { type: 'required', message: 'Campo obligatorio' }
      ],
      'password': [
        { type: 'minlength', message: 'La password debe contener un mínimo de 8 caracteres' },
        { type: 'required', message: 'Campo obligatorio' },
        { type: 'pattern', message: 'La password debe contener una combinación de letras mayúsculas y minúsculas, números y alguno de los siguientes caracteres especiales: !@#\$%\^&\*' }
      ],
      'password2': [
        { type: 'required', message: 'Campo obligatorio' },
      ],
      'passwords_coincidentes': [
        { type: 'coinciden', message: 'Las passwords no coinciden' }
      ],

    }
  }

  registrar() {

    this.usuario = new Usuario();
    this.usuario.dni = this.registroForm.controls.dni.value;
    this.usuario.nombre = this.registroForm.controls.nombre.value;
    this.usuario.apellidos = this.registroForm.controls.apellidos.value;
    this.usuario.email = this.registroForm.controls.email.value;
    this.usuario.telefonoContacto = this.registroForm.controls.telefonoContacto.value;
    this.usuario.marcaVehiculo = this.registroForm.controls.marcaVehiculo.value;
    this.usuario.password = this.grupo_passwords.controls.password.value;
    this.registroService.registrar(this.usuario).subscribe(
      data => {
        console.log(data);
        this.successResponse = true;
        this.mensajeResultado = "Su usuario ha sido creado con éxito";
        setTimeout(() => {
          this.navCtrl.setRoot(LoginPage);
        },
          5000);
      },
      error => {
        console.log(error)
        if (error.error.errorCode === "1001") {
          console.log(error.error.errorMessage);
          this.errorResponse = true;
          this.mensajeResultado = error.error.errorMessage;
        }
      });
  }
}