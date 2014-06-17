var __extends = this.__extends || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    __.prototype = b.prototype;
    d.prototype = new __();
};
define(["require", "exports", "comm/Command"], function(require, exports, cmd) {
    (function (dolphin) {
        var PresentationModelResetedCommand = (function (_super) {
            __extends(PresentationModelResetedCommand, _super);
            function PresentationModelResetedCommand(pmId) {
                _super.call(this);
                this.pmId = pmId;
                this.id = 'PresentationModelReseted';
                this.className = "org.opendolphin.core.comm.PresentationModelResetedCommand";
            }
            return PresentationModelResetedCommand;
        })(cmd.dolphin.Command);
        dolphin.PresentationModelResetedCommand = PresentationModelResetedCommand;
    })(exports.dolphin || (exports.dolphin = {}));
    var dolphin = exports.dolphin;
});

