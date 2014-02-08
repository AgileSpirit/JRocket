'use strict';

describe('Controller: BookmarksCtrl', function () {

  // load the controller's module
  beforeEach(module('jrocketApp'));

  var BookmarksCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    BookmarksCtrl = $controller('BookmarksCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
