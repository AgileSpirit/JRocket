'use strict';

angular.module('jrocketApp')
  .controller('BookmarkListController', ['$scope', '$rootScope', 'BookmarkService',
    function ($scope, $rootScope, bookmarkService) {

      // Instantiate an object to store your scope data in (Best Practices)
      $scope.bookmarks = {};

      $scope.removeBookmark = function (bookmark) {
        // Server call
        bookmarkService.remove({id: bookmark.id},
          function () {
            // Client update
            $scope.bookmarks = bookmarkService.query();
          },
          function () {
            console.log('An error occurred during bookmark deleting');
          });
      };

      $scope.editBookmark = function (id) {
        $rootScope.$emit('openBookmarkWizardEvent', id);
      };

      $rootScope.$on('refreshBookmarkList', function(){
        refreshBookmarkList();
      });

      function refreshBookmarkList() {
        $scope.bookmarks = bookmarkService.query();
      }

      refreshBookmarkList();

    }]);
