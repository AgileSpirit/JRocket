'use strict';

/* Controllers */

angular.module('impulse.controllers', []).
  controller('BookmarkListCtrl', ['$scope', 'Bookmarks', function($scope, Bookmarks) {
    // Instantiate an object to store your scope data in (Best Practices)
    $scope.data = {};
   
    Bookmarks.query(function(response) {
      // Assign the response INSIDE the callback
      $scope.data.bookmarks = response;
    });
  }])
  .controller('BookmarkDetailCtrl', ['$scope', '$routeParams', function($scope, $routeParams) {
	  $scope.bookmarkId = $routeParams.bookmarkId;
  }]);