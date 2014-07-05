'use strict';

angular.module('jrocketApp')
  .controller('BookmarkListController', ['$scope', '$rootScope', '$modal', 'BookmarkService',
    function ($scope, $rootScope, $modal, bookmarkService) {

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

      $scope.editBookmark = function(id) {
        $modal.open({
          templateUrl: 'views/bookmark-wizard.html',
          controller: 'BookmarkWizardController',
          resolve: {
            bookmarkId: function(){
              return id;
            }
          }
        });
      };

      $scope.searchBookmarks = function(query) {
          $scope.bookmarks = bookmarkService.search(query, 0, 3);
      };

      $rootScope.$on('refreshBookmarkList', function(){
        refreshBookmarkList();
      });

      function refreshBookmarkList() {
        $scope.bookmarks = bookmarkService.query();
      }

      refreshBookmarkList();

    }]);
