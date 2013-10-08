'use strict';

/* Services */


// Demonstrate how to register services
// In this case it is a simple value service.
angular.module('impulse.services', ['ngResource'])
  .factory('Bookmarks', function($resource) {
    return $resource('http://localhost\\:8080/JavaBackbone/service/bookmarks/', {});
  })
  .value('version', '0.1');