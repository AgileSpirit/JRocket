'use strict';

describe('Service: Bookmarkservice', function () {

  // load the service's module
  beforeEach(module('jrocketUiApp'));

  // instantiate service
  var Bookmarkservice;
  beforeEach(inject(function (_Bookmarkservice_) {
    Bookmarkservice = _Bookmarkservice_;
  }));

  it('should do something', function () {
    expect(!!Bookmarkservice).toBe(true);
  });

});
