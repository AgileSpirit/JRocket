'use strict';

/* Services */

// Demonstrate how to register services
// In this case it is a simple value service.
var app = angular.module('jrocketServices', [ 'ngResource' ]);

app.factory('bookmarkService', function ($resource) {
    return $resource('./service/bookmarks/:id', { id: '@id' }, {
        update: {method: 'PUT', params: { id: '@id' } }
    });
});