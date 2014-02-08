'use strict';

describe('Service: Bookmarksservice', function () {

  // load the service's module
  beforeEach(module('jrocketApp'));

  // instantiate service
  var Bookmarksservice;
  beforeEach(inject(function (_Bookmarksservice_) {
    Bookmarksservice = _Bookmarksservice_;
  }));

  it('should do something', function () {
    expect(!!Bookmarksservice).toBe(true);
  });

});
