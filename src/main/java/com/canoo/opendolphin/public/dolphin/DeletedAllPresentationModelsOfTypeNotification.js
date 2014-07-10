var __extends = this.__extends || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    __.prototype = b.prototype;
    d.prototype = new __();
};
define(["require", "exports", "dolphin/Command"], function(require, exports, cmd) {
    (function (dolphin) {
        var DeletedAllPresentationModelsOfTypeNotification = (function (_super) {
            __extends(DeletedAllPresentationModelsOfTypeNotification, _super);
            function DeletedAllPresentationModelsOfTypeNotification(pmType) {
                _super.call(this);
                this.pmType = pmType;
                this.id = 'DeletedAllPresentationModelsOfType';
                this.className = "org.opendolphin.core.dolphin.DeletedAllPresentationModelsOfTypeNotification";
            }
            return DeletedAllPresentationModelsOfTypeNotification;
        })(cmd.dolphin.Command);
        dolphin.DeletedAllPresentationModelsOfTypeNotification = DeletedAllPresentationModelsOfTypeNotification;
    })(exports.dolphin || (exports.dolphin = {}));
    var dolphin = exports.dolphin;
});

