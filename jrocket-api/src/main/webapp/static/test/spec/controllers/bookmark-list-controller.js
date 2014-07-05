'use strict';

describe('Controller: BookmarkListController', function () {

  // load the controller's module
  beforeEach(module('jrocketApp'));

  var BookmarkListController, scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    BookmarkListController = $controller('BookmarkListController', {
      $scope: scope
    });
  }));

});
