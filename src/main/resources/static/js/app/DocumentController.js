'use strict';

angular.module('crudApp').controller('DocumentController',
    ['DocumentService', '$scope', '$stateParams',  function( DocumentService, $scope, $stateParams) {
    	
    	var self = this;
        self.document = {};
        self.documents=[];
        self.patientId=$stateParams.patientId;
        
        self.submit = submit;
        self.getAllDocuments = getAllDocuments;
        self.createDocument = createDocument;
        self.updateDocument = updateDocument;
        self.removeDocument = removeDocument;
        self.editDocument = editDocument;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;
        
        self.onlyIntegers = /^\d+$/;
        
        function getAllDocuments(){
            return DocumentService.getAllDocuments();
        }
        
        function createDocument(document) {
            console.log('Se creara un Documento');
            DocumentService.createDocument(self.patientId, document)
                .then(
                    function (response) {
                        console.log('Documento creado correctamente');
                        self.successMessage = 'Documento creado correctamente';
                        self.errorMessage='';
                        self.done = true;
                        self.patient={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error al crear el Documento');
                        self.errorMessage = 'Error al crear el Documento: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }
        
        function updateDocument(document, idDocument){
        	DocumentService.updateDocument(document, idDocument, self.patientId)
                .then(
                    function (response){
                        console.log('Documento actualizado correctamente');
                        self.successMessage='Documento actualizado correctamente';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Hubo un error al actualizar el Documento');
                        self.errorMessage='Hubo un error al actualizar el Documento '+errResponse.data;
                        self.successMessage='';
                    }
                );
        }
        
        function removeDocument(idDocument){
        	DocumentService.removeDocument(idDocument, self.patientId)
                .then(
                    function(){
                        console.log('Documento '+idDocument + ' eliminado correctamente');
                    },
                    function(errResponse){
                        console.error('Hubo un error al eliminar el Documento '+idDocument +', Error :'+errResponse.data);
                    }
                );
        }
        
        function editDocument(idDocument) {
            self.successMessage='';
            self.errorMessage='';
            DocumentService.getDocument(idDocument).then(
                function (document) {
                    self.document = document;
                },
                function (errResponse) {
                    console.error('Error al obtener el documento ' + idDocument + ', Error :' + errResponse.data);
                }
            );
        }
        
        function submit() {
        	if (self.document.idDocumentSummary === null || self.document.idDocumentSummary === undefined) {
        		console.log('Creando nuevo Documento', self.document);
                createDocument(self.document);
            } else {
                updateDocument(self.document, self.document.idDocumentSummary);
                console.log('Actualizando Documento', self.document.idDocumentSummary);
            }
        }
        
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.document={};
            $scope.myForm.$setPristine(); //reset Form
        }

    }


]);