'use strict';

/* Controllers */

angular.module('impulse.controllers', [])
  .controller('BookmarkListCtrl', ['$scope', 'bookmarkService', function($scope, bookmarkService) {
    // Instantiate an object to store your scope data in (Best Practices)
    $scope.data = {};
   
//    var bookmarks = $scope.data.bookmarks = bookmarkResource.query();
    
    bookmarkService.query(function(response) {
      // Assign the response INSIDE the callback
      $scope.data.bookmarks = response;
    });
    
    $scope.getBookmark = function(id) {
      bookmarkService.get(id, function(response) {
        console.log("Kiwi!");
      });
    };
    
    $scope.removeBookmark = function(bookmark) {
      console.log("$scope.removeBookmark, id=" + bookmark.id);
      bookmarkService.remove({id:bookmark.id}, function() {
        bookmarks.splice(bookmarks.indexOf(bookmark), 1);
      });
    };
  }])
  .controller('BookmarkDetailCtrl', ['$scope', '$routeParams', function($scope, $routeParams) {
	  $scope.bookmarkId = $routeParams.bookmarkId;
  }]);