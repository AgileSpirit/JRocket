'use strict';

var baseUrl = 'http://localhost:8080/JRocket';

angular.module('jrocketApp')
  .service('BookmarkService', function ($resource) {
    return $resource(baseUrl + '/api/bookmarks/:id', { id: '@id' }, {
      update: {method: 'PUT', params: { id: '@id' } }
    });
  });
