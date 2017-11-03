'use strict';

angular.module('crudApp').factory('DocumentService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

    	var factory = {
    			loadAllDocuments: loadAllDocuments,
            	getAllDocuments: getAllDocuments,
            	getDocument: getDocument,
            	createDocument: createDocument,
            	updateDocument: updateDocument,
            	removeDocument: removeDocument
            };

            return factory;
            
            function loadAllDocuments(idPatient) {
                console.log(urls.GETALL_DOCUMENTS + idPatient);
                var deferred = $q.defer();
                $http.get(urls.GETALL_DOCUMENTS + idPatient)
                    .then(
                        function (response) {
                            console.log('Se han obtenido todos los documentos correctamente ' );
                            $localStorage.documents = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Hubo un error al obtener los documentos');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
            
            function getAllDocuments(){
                return $localStorage.documents;
            }
            
            function getDocument(idDocument) {
                console.log('Obtieniendo Documento con el id :'+idDocument);
                var deferred = $q.defer();
                $http.get(urls.BASE_DOCUMENT + idDocument)
                    .then(
                        function (response) {
                            console.log('Se ha obtenido Documento : '+idDocument);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error al obtener Documento con el id :'+idDocument);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
            
            function createDocument(idPatient, document) {
            	document.documentDate = new Date(document.documentDate).getTime();
            	document.file.fileType = document.file.filetype;
            	document.file.fileSize = document.file.filesize;
            	document.file.fileName = document.file.filename;
            	
                console.log('Creando Documento '+ document);
                
                var deferred = $q.defer();
                $http.post( urls.CREATE_DOCUMENT + idPatient, document)
                    .then(
                        function (response) {
                        	loadAllDocuments(idPatient);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Hubo un error al crear el documento : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
            
            function updateDocument(document, idDocument, idPatient) {
            	document.documentDate = new Date(document.documentDate).getTime();
                console.log('Actualizando Documento : '+idDocument);
                var deferred = $q.defer();
                $http.put(urls.UPDATE_DOCUMENT + idDocument, document)
                    .then(
                        function (response) {
                        	loadAllDocuments(idPatient);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error al actualizar documento : '+idDocument);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
 
            function removeDocument(idDocument, idPatient) {
                console.log('Eliminando Documento : '+idDocument);
                var deferred = $q.defer();
                $http.delete(urls.DELETE_DOCUMENT + idDocument)
                    .then(
                        function (response) {
                        	loadAllDocuments(idPatient);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error al eliminar Documento : '+idDocument);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
           

        }
    ]);