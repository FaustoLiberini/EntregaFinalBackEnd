window.addEventListener('load', function () {


    const formulario = document.querySelector('#add_new_odontologo');

    formulario.addEventListener('submit', function (event) {
    console.log('Formulario enviado');

        const formData = {
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            matricula: document.querySelector('#matricula').value,
        };
         console.log('Datos del formulario:', formData);

        const url = 'http://localhost:8080/odontologos/registrar';

        const settings = {
        method: 'POST',
           headers: {
                'Content-Type': 'application/json'
            },
        body: JSON.stringify(formData)
    }

    fetch(url, settings)
        .then(response => response.json())
        .then(data => {


                let successAlert = '<div class="alert alert-success alert-dismissible">' +

                    '<button type="button" class="close" ' +
                    'data-dismiss="alert">&times;</button>' +

                    '<strong> Odontologo Agregado </strong></div>'


                document.querySelector('#response').innerHTML = successAlert;

                document.querySelector('#response').style.display = "block";

                resetUploadForm();
            })
            .catch(error => {
                 console.error('Error al enviar la solicitud:', error);

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