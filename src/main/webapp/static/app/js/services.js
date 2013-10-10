'use strict';

/* Services */

// Demonstrate how to register services
// In this case it is a simple value service.
angular.module('impulse.services', [ 'ngResource' ]).factory('bookmarkService', function($resource) {
  var bookmarkService = $resource('/JavaBackbone/service/bookmarks/:id', {id:'@id'});
  return bookmarkService;
}).value('version', '0.1');