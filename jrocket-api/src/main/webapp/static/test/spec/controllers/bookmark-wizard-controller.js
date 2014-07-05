'use strict';

describe('Controller: BookmarkWizardController', function () {

  // load the controller's module
  beforeEach(module('jrocketApp'));

  var BookmarkWizardController, scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    BookmarkWizardController = $controller('BookmarkWizardController', {
      $scope: scope
    });
  }));

});
