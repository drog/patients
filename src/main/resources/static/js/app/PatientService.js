'use strict';

angular.module('crudApp').factory('PatientService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
            	loadAllPatients: loadAllPatients,
            	getAllPatients: getAllPatients,
            	getPatient: getPatient,
                createPatient: createPatient,
                updatePatient: updatePatient,
                removePatient: removePatient
            };

            return factory;

            function loadAllPatients() {
                console.log('Fetching all users');
                var deferred = $q.defer();
                $http.get(urls.GETALL)
                    .then(
                        function (response) {
                            console.log('Se han obtenido todos los pacientes correctamente');
                            $localStorage.patients = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Hubo un error al obtener los pacientes');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllPatients(){
                return $localStorage.patients;
            }
            
            function getPatient(idPatient) {
                console.log('Obtieniendo Paciente con el id :'+idPatient);
                var deferred = $q.defer();
                $http.get(urls.BASE + idPatient)
                    .then(
                        function (response) {
                            console.log('Se ha obtenido paciente : '+idPatient);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error al obtener paciente con el id :'+idPatient);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createPatient(patient) {
                console.log('Creando paciente '+ patient);
                var deferred = $q.defer();
                $http.post( urls.CREATE, patient)
                    .then(
                        function (response) {
                        	loadAllPatients();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Hubo un error al crear el paciente : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
            
            function updatePatient(patient, idPatient) {
                console.log('Actualizando Paciente : '+idPatient);
                var deferred = $q.defer();
                $http.put(urls.UPDATE + idPatient, patient)
                    .then(
                        function (response) {
                        	loadAllPatients();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error al actualizar paciente : '+idPatient);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
            function removePatient(idPatient) {
                console.log('Eliminando Paciente : '+idPatient);
                var deferred = $q.defer();
                $http.delete(urls.DELETE + idPatient)
                    .then(
                        function (response) {
                            loadAllPatients();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error al eliminar paciente : '+idPatient);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);