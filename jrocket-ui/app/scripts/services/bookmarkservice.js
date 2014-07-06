'use strict';

/**
 * @ngdoc service
 * @name jrocketUiApp.Bookmarkservice
 * @description
 * # Bookmarkservice
 * Service in the jrocketUiApp.
 */
angular.module('jrocketUiApp')
    .service('BookmarkService', function BookmarkService($resource) {
        var endPoint = 'http://localhost:8080/jrocket/api/bookmarks';

        return $resource(endPoint, {}, {
            save: { method: 'POST', url: endPoint },
            update: { method: 'PUT', url: endPoint + '/:id', params: { id: '@id' } },
            search: { method: 'GET', url: endPoint + '/search?q=:query&o=:offset&s=:size' }
        });
    });
