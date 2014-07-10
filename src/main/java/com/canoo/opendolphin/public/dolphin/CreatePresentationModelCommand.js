var __extends = this.__extends || function (d, b) {
	for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
	function __() { this.constructor = d; }
	__.prototype = b.prototype;
	d.prototype = new __();
};
define(["require", "exports", "dolphin/Command"], function(require, exports, cmd) {
	(function (dolphin) {
		var CreatePresentationModelCommand = (function (_super) {
			__extends(CreatePresentationModelCommand, _super);
			function CreatePresentationModelCommand(presentationModel) {
				_super.call(this);
				this.attributes = [];
				this.clientSideOnly = false;
				this.id = "CreatePresentationModel";
				this.className = "org.opendolphin.core.dolphin.CreatePresentationModelCommand";
				this.pmId = presentationModel.id;
				this.pmType = presentationModel.presentationModelType;

				var attrs = this.attributes;
				presentationModel.getAttributes().forEach(function (attr) {
					attrs.push({
						propertyName: attr.propertyName,
						id: attr.id,
						qualifier: attr.getQualifier(),
						value: attr.getValue(),
						tag: attr.tag
					});
				});
			}
			return CreatePresentationModelCommand;
		})(cmd.dolphin.Command);
		dolphin.CreatePresentationModelCommand = CreatePresentationModelCommand;
	})(exports.dolphin || (exports.dolphin = {}));
	var dolphin = exports.dolphin;
});

