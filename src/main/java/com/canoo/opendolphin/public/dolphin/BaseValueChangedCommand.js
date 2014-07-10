var __extends = this.__extends || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    __.prototype = b.prototype;
    d.prototype = new __();
};
define(["require", "exports", "dolphin/Command"], function(require, exports, cmd) {
    (function (dolphin) {
        var BaseValueChangedCommand = (function (_super) {
            __extends(BaseValueChangedCommand, _super);
            function BaseValueChangedCommand(attributeId) {
                _super.call(this);
                this.attributeId = attributeId;
                this.id = 'BaseValueChanged';
                this.className = "org.opendolphin.core.dolphin.BaseValueChangedCommand";
            }
            return BaseValueChangedCommand;
        })(cmd.dolphin.Command);
        dolphin.BaseValueChangedCommand = BaseValueChangedCommand;
    })(exports.dolphin || (exports.dolphin = {}));
    var dolphin = exports.dolphin;
});

