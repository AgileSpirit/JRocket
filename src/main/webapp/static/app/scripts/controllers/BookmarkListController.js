'use strict';

angular.module('jrocketApp')
  .controller('BookmarkListController', ['$scope', 'BookmarkService',
    function ($scope, bookmarkService) {

      // Instantiate an object to store your scope data in (Best Practices)
      $scope.data = {};

      $scope.data.bookmarks = bookmarkService.query();

      $scope.removeBookmark = function (bookmark) {
        // Server call
        bookmarkService.remove({id: bookmark.id},
          function (value, responseHeaders) {
            // Client update
            $scope.data.bookmarks = bookmarkService.query();
          },
          function (httpResponse) {
            console.log('An error occured during bookmark delete');
          });
      };

//      $scope.editBookmark = function (id) {
//        $rootScope.emit('wizard.open', id);
//      };

    }]);
