'use strict';

/* Controllers */

angular.module('jws.controllers', [])
  .controller('BookmarkListCtrl', ['$scope', 'bookmarkService', function($scope, bookmarkService) {
    // Instantiate an object to store your scope data in (Best Practices)
    $scope.data = {};
   
    bookmarkService.query(function(response) {
      // Assign the response INSIDE the callback
      $scope.data.bookmarks = response;
    });
    
    $scope.getBookmark = function(id) {
      var bookmark = bookmarkService.get(id);
      console.log("bookmark = " + bookmark.id);
    };
    
        
    $scope.removeBookmark = function(bookmark) {
      console.log("$scope.removeBookmark, id=" + bookmark.id);
      bookmarkService.remove({id:bookmark.id}, function() {
          var bookmarks = $scope.data.bookmarks;
          bookmarks.splice(bookmarks.indexOf(bookmark), 1);
      });
    };
  }])
  .controller('BookmarkDetailCtrl', ['$scope', '$routeParams', function($scope, $routeParams) {
	  $scope.bookmarkId = $routeParams.bookmarkId;
  }]);