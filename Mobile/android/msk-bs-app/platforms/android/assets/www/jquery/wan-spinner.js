;(function($, window, document, undefined) {
  'use strict';

  var output = function(msg) {
    window.console && console.log(msg);
  };

  var WanSpinner = function(element, options) {
    this.defaults = {
      maxValue: 200000,
      minValue: 1,
      step: 1,
      start: 1,
      inputWidth: 40,
      plusClick: function(element, val) {
        return true;
      },
      minusClick: function(element, val) {
        return true;
      },
      exceptionFun: function(exp) {
        return true;
      },
      valueChanged: function(element, val) {
        var input = element;
        var pdCode = input.parent().parent().prev().val();
        BA2141109.getMaxStockQty(pdCode,input,val);
        if(!input.val() || parseInt(input.val()-1) < 1){
          webToast("宝贝数量不能再减少了","middle");
          return false;
        }
        return true;
      }
    };
    this.options = $.extend({}, this.defaults, options);
    this.options.stepLength = ((+this.options.step).toString().split('.')[1] || '').length;
    this.options.stepFloat = parseInt(1 * Math.pow(10, this.options.stepLength) / this.options.step) || 1;
    this.element = $(element);

    this.element.each(function(index, dt) {
      var input = $(dt).children('input');
      var initValue = input.val() || this.options.start;
      input.val(initValue);
    });

    this.element.children('input').css('width', this.options.inputWidth);

    this.EXCEPTION = {
      TOO_LARGE: 1,
      NORMAL: 0,
      TOO_SMALL: -1
    };
  };

  WanSpinner.prototype.bind = function() {
    var self = this;
    $(self.element).delegate('.minus', 'click', function() {
      var val;
      var input = $(this).siblings('input');
      if(!input.val() || parseInt(input.val()-1) < 1){
        webToast("宝贝数量不能再减少了","middle");
        return false;
      }
      if (self.options.stepFloat > 1) {
        val = (+input.val() || 0) * self.options.stepFloat - self.options.step  * self.options.stepFloat;
        val = Math.round(val) / self.options.stepFloat;
      } else {
        val = (+input.val() || 0) - self.options.step;
      }
      val = val.toFixed(self.options.stepLength);
      if (val < self.options.minValue) {
        typeof(self.options.exceptionFun) === 'function' && self.options.exceptionFun(self.EXCEPTION.TOO_SMALL);
      } else {
        input.val(val);
        typeof(self.options.minusClick) === 'function' && self.options.minusClick($(this).parent(), val);
        typeof(self.options.valueChanged) === 'function' && self.options.valueChanged(input, val);
      }
      return false;
    }).delegate('.plus', 'click', function() {
      var val;
      var input = $(this).siblings('input');
      //var pdCode = $(this).parent().parent().prev().val();
      /*BA2141109.getMaxStockQty(pdCode,input,input.val());
      if(!input.val() || parseInt(input.val()-1) < 1){
        webToast("宝贝数量不能再减少了","middle");
        return false;
      }*/
      if (self.options.stepFloat > 1) {
        val = (+input.val() || 0) * self.options.stepFloat + self.options.step * self.options.stepFloat;
        val = Math.round(val) / self.options.stepFloat;
      } else {
        val = (+input.val() || 0) + self.options.step;
      }
      val = val.toFixed(self.options.stepLength);
      if (val > self.options.maxValue) {
        typeof(self.options.exceptionFun) === 'function' && self.options.exceptionFun(self.EXCEPTION.TOO_LARGE);
      } else {
        input.val(val);
        typeof(self.options.plusClick) === 'function' && self.options.plusClick($(this).parent(), val);
        typeof(self.options.valueChanged) === 'function' && self.options.valueChanged(input, val);
      }
      return false;
    }).delegate('input', 'change', function() {
      var val = +$(this).val() || 0;
      if (val > self.options.maxValue) {
        val = self.options.maxValue;
        typeof(self.options.exceptionFun) === 'function' && self.options.exceptionFun(self.EXCEPTION.TOO_LARGE);
      } else if (val < self.options.minValue) {
        val = self.options.minValue;
        typeof(self.options.exceptionFun) === 'function' && self.options.exceptionFun(self.EXCEPTION.TOO_SMALL);
      }
      $(this).val(val);
      typeof(self.options.valueChanged) === 'function' && self.options.valueChanged($(this), val);
    });
  }


  $.fn.WanSpinner = function(options) {
    var wanSpinner = new WanSpinner(this, options);
    wanSpinner.bind();
    return this;
  };

})(jQuery, window, document);
