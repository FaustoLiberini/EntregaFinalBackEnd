window.addEventListener('load', function () {


    const formulario = document.querySelector('#add_new_odontologo');

    formulario.addEventListener('submit', function (event) {

        const formData = {
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            matricula: document.querySelector('#matricula').value,
        };
        const url = 'http://localhost:8080/odontologos/registrar';

        const settings = {
        method: 'POST',
        body: JSON.stringify(formData)
    }
    fetch(url, settings)
    .then(response => response.json())
    .then(data => {

                //Si no hay ningún error,
                //se muestra un mensaje diciendo que la película
                //fue agregada
                let successAlert = '<div class="alert alert-success alert-dismissible">' +

                    '<button type="button" class="close" ' +
                    'data-dismiss="alert">&times;</button>' +

                    '<strong> Odontologo Agregado </strong></div>'


                document.querySelector('#response').innerHTML = successAlert;

                document.querySelector('#response').style.display = "block";

                resetUploadForm();
            })
            .catch(error => {
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +

                                '<button type="button" class="close"' +
                                'data-dismiss="alert">&times;</button>' +

                                '<strong> Error al registrar, vuelva a intentar</strong> </div>'
                                document.querySelector('#response').innerHTML = errorAlert;
                                document.querySelector('#response').style.display = "block";

                    resetUploadForm();})
                });

                   function resetUploadForm(){

                       document.querySelector('#nombre').value = "";

                       document.querySelector('#apellido').value = "";

                       document.querySelector('#matricula').value = "";

                   }
    })