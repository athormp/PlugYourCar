import { FormControl, FormGroup } from '@angular/forms';

export class DniValidator {

    //validador para el campo DNI/NIE en la pantalla de registro

    static validar(formControl: FormControl) : any {
        if (formControl.value.length < 9)
            return null;  
        let numero, letraDni, letra;
        var expresion_regular_dni = /^[XYZ]?\d{5,8}[A-Z]$/;
        var result;
        let value = formControl.value.toUpperCase();
        console.log(value);
        if (expresion_regular_dni.test(value) === true) {
            numero = value.substr(0, value.length - 1);
            numero = numero.replace('X', 0);
            numero = numero.replace('Y', 1);
            numero = numero.replace('Z', 2);
            letraDni = value.substr(value.length - 1, 1);
            numero = numero % 23;
            letra = 'TRWAGMYFPDXBNJZSQVHLCKET';
            letra = letra.substring(numero, numero + 1);
            if (letra != letraDni) {
                console.log("DNI erróneo");
                return {
                    validar: true
                }
            } else {
                return null;
            }
        } else {
            return {
                validar: true
            }
        }
    }
}