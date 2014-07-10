define(["require", "exports", "dolphin/NamedCommand", "dolphin/SignalCommand", "dolphin/EmptyNotification", "dolphin/ClientPresentationModel", "dolphin/ClientAttribute", "dolphin/AttributeCreatedNotification"], function(require, exports, namedCmd, signlCmd, emptyNot, pm, ca, acn) {
	(function (dolphin) {
		var ClientDolphin = (function () {
			function ClientDolphin() {
			}
			ClientDolphin.prototype.setClientConnector = function (clientConnector) {
				this.clientConnector = clientConnector;
			};

			ClientDolphin.prototype.getClientConnector = function () {
				return this.clientConnector;
			};

			ClientDolphin.prototype.send = function (commandName, onFinished) {
				this.clientConnector.send(new namedCmd.dolphin.NamedCommand(commandName), onFinished);
			};

			ClientDolphin.prototype.sendEmpty = function (onFinished) {
				this.clientConnector.send(new emptyNot.dolphin.EmptyNotification(), onFinished);
			};

			// factory method for attributes
			ClientDolphin.prototype.attribute = function (propertyName, qualifier, value, tag) {
				return new ca.dolphin.ClientAttribute(propertyName, qualifier, value, tag);
			};

			// factory method for presentation models
			ClientDolphin.prototype.presentationModel = function (id, type) {
				var attributes = [];
				for (var _i = 0; _i < (arguments.length - 2); _i++) {
					attributes[_i] = arguments[_i + 2];
				}
				var model = new pm.dolphin.ClientPresentationModel(id, type);
				if (attributes && attributes.length > 0) {
					attributes.forEach(function (attribute) {
						//OD-GWT: start change:
						if (attribute.constructor.name == 'Array') {
							attribute.forEach(function(elem) {
								model.addAttribute(elem);
							})
						} else {
							model.addAttribute(attribute);
						}
						//OD-GWT: end change.

					});
				}
				this.getClientModelStore().add(model);
				return model;
			};

			ClientDolphin.prototype.setClientModelStore = function (clientModelStore) {
				this.clientModelStore = clientModelStore;
			};

			ClientDolphin.prototype.getClientModelStore = function () {
				return this.clientModelStore;
			};

			ClientDolphin.prototype.listPresentationModelIds = function () {
				return this.getClientModelStore().listPresentationModelIds();
			};

			ClientDolphin.prototype.listPresentationModels = function () {
				return this.getClientModelStore().listPresentationModels();
			};

			ClientDolphin.prototype.findAllPresentationModelByType = function (presentationModelType) {
				return this.getClientModelStore().findAllPresentationModelByType(presentationModelType);
			};

			ClientDolphin.prototype.getAt = function (id) {
				return this.findPresentationModelById(id);
			};

			ClientDolphin.prototype.findPresentationModelById = function (id) {
				return this.getClientModelStore().findPresentationModelById(id);
			};
			ClientDolphin.prototype.deletePresentationModel = function (modelToDelete) {
				this.getClientModelStore().deletePresentationModel(modelToDelete, true);
			};

			ClientDolphin.prototype.deleteAllPresentationModelOfType = function (presentationModelType) {
				this.getClientModelStore().deleteAllPresentationModelOfType(presentationModelType);
			};

			ClientDolphin.prototype.updatePresentationModelQualifier = function (presentationModel) {
				var _this = this;
				presentationModel.getAttributes().forEach(function (sourceAttribute) {
					_this.updateAttributeQualifier(sourceAttribute);
				});
			};

			ClientDolphin.prototype.updateAttributeQualifier = function (sourceAttribute) {
				if (!sourceAttribute.getQualifier())
					return;
				var attributes = this.getClientModelStore().findAllAttributesByQualifier(sourceAttribute.getQualifier());
				attributes.forEach(function (targetAttribute) {
					if (targetAttribute.tag != sourceAttribute.tag)
						return;
					targetAttribute.setValue(sourceAttribute.getValue()); // should always have the same value
					targetAttribute.setBaseValue(sourceAttribute.getBaseValue()); // and same base value and so dirtyness
				});
			};

			ClientDolphin.prototype.tag = function (presentationModel, propertyName, value, tag) {
				var clientAttribute = new ca.dolphin.ClientAttribute(propertyName, null, value, tag);
				this.addAttributeToModel(presentationModel, clientAttribute);
				return clientAttribute;
			};

			ClientDolphin.prototype.addAttributeToModel = function (presentationModel, clientAttribute) {
				presentationModel.addAttribute(clientAttribute);
				this.getClientModelStore().registerAttribute(clientAttribute);
				if (!presentationModel.clientSideOnly) {
					this.clientConnector.send(new acn.dolphin.AttributeCreatedNotification(presentationModel.id, clientAttribute.id, clientAttribute.propertyName, clientAttribute.getValue(), clientAttribute.getQualifier(), clientAttribute.tag), null);
				}
			};

			////// push support ///////
			ClientDolphin.prototype.startPushListening = function (pushActionName, releaseActionName) {
				this.clientConnector.setPushListener(new namedCmd.dolphin.NamedCommand(pushActionName));
				this.clientConnector.setReleaseCommand(new signlCmd.dolphin.SignalCommand(releaseActionName));
				this.clientConnector.setPushEnabled(true);
				this.clientConnector.listen();
			};
			ClientDolphin.prototype.stopPushListening = function () {
				this.clientConnector.setPushEnabled(false);
			};
			return ClientDolphin;
		})();
		dolphin.ClientDolphin = ClientDolphin;
	})(exports.dolphin || (exports.dolphin = {}));
	var dolphin = exports.dolphin;
});

