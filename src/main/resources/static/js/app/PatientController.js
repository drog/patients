'use strict';

angular.module('crudApp').controller('PatientController',
    ['PatientService', '$scope',  function( PatientService, $scope) {

        var self = this;
        self.patient = {};
        self.patients=[];

        self.submit = submit;
        self.getAllPatients = getAllPatients;
        self.createPatient = createPatient;
        self.updatePatient = updatePatient;
        self.removePatient = removePatient;
        self.editPatient = editPatient;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;

        function submit() {
        	if (self.patient.idPatient === undefined || self.patient.idPatient === null) {
        		console.log('Creando nuevo paciente', self.patient);
                createPatient(self.patient);
            } else {
                updatePatient(self.patient, self.patient.idPatient);
                console.log('Actualizando paciente', self.patient.idPatient);
            }
        }

        function createPatient(patient) {
            console.log('Se creara un paciente');
            PatientService.createPatient(patient)
                .then(
                    function (response) {
                        console.log('Paciente creado correctamente');
                        self.successMessage = 'Paciente creado correctamente';
                        self.errorMessage='';
                        self.done = true;
                        self.patient={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error al crear el paciente');
                        self.errorMessage = 'Error al crear el paciente: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }

        function getAllPatients(){
            return PatientService.getAllPatients();
        }
        
        function updatePatient(patient, idPatient){
        	PatientService.updatePatient(patient, idPatient)
                .then(
                    function (response){
                        console.log('Paciente actualizado correctamente');
                        self.successMessage='Paciente actualizado correctamente';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Hubo un error al actualizar el paciente');
                        self.errorMessage='Hubo un error al actualizar el paciente '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }
        
        function removePatient(idPatient){
        	PatientService.removePatient(idPatient)
                .then(
                    function(){
                        console.log('Paciente '+idPatient + ' eliminado correctamente');
                    },
                    function(errResponse){
                        console.error('Hubo un error al eliminar el paciente '+idPatient +', Error :'+errResponse.data);
                    }
                );
        }
        
        function editPatient(idPatient) {
            self.successMessage='';
            self.errorMessage='';
            PatientService.getPatient(idPatient).then(
                function (patient) {
                    self.patient = patient;
                },
                function (errResponse) {
                    console.error('Error al obtener el paciente ' + idPatient + ', Error :' + errResponse.data);
                }
            );
        }
        
        
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.patient={};
            $scope.myForm.$setPristine(); //reset Form
        }
    }


    ]);