import { FormControl, FormGroup } from '@angular/forms';

export class PasswordValidator {

    //validador para los campos contraseña en la pantalla de registro

    static coinciden(formGroup: FormGroup) : any {
        let valor;
        let coincidentes = true;

        for (let key in formGroup.controls) {
            console.log("ENTRO");
            if (formGroup.controls.hasOwnProperty(key)) {
                let valorControl: FormControl = <FormControl>formGroup.controls[key];
                if (valor === undefined) {
                    valor = valorControl.value;
                } else {
                    console.log(valor);
                    if (valor !== valorControl.value) {
                        coincidentes = false;
                        break;
                    }
                }
            }
        }
        if (coincidentes) {
            return null;
        }
        return {
            coinciden: true
        }
    }
}