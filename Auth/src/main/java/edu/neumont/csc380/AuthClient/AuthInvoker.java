package edu.neumont.csc380.AuthClient;

import javax.ws.rs.core.Response;

import edu.neumont.csc380.AuthServer.AuthPayload;
import edu.neumont.csc380.AuthServer.Credentials;
import edu.neumont.csc380.AuthServer.IAuthenticationService;
import edu.neumont.csc380.AuthServer.IAuthorizationService;

public enum AuthInvoker {
	LOGIN {
		@Override
		public Response invoke(IAuthenticationService authenticate,
				IAuthorizationService authorization, AuthPayload payload,
				Credentials creds) throws Exception {
			
			return authenticate.handleLoginRequest(creds);
		}
	},
	LOGOUT {
		@Override
		public Response invoke(IAuthenticationService authenticate,
				IAuthorizationService authorization, AuthPayload payload,
				Credentials creds) throws Exception {
			return authenticate.handleLogoutRequest(payload);
		}
	},
	CREATE_AUTH {
		@Override
		public Response invoke(IAuthenticationService authenticate,
				IAuthorizationService authorization, AuthPayload payload,
				Credentials creds) throws Exception {
			return authenticate.handleCreationRequest(creds);
		}
	},
	UPDATE_AUTH {
		@Override
		public Response invoke(IAuthenticationService authenticate,
				IAuthorizationService authorization, AuthPayload payload,
				Credentials creds) throws Exception {
			return authorization.handleUpdateRequest(payload);
		}
	},
	CHECK_AUTH {
		@Override
		public Response invoke(IAuthenticationService authenticate,
				IAuthorizationService authorization, AuthPayload payload,
				Credentials creds) throws Exception {
			return authorization.handleAuthorizationRequest(payload);
		}
	},
	DELETE_AUTH {
		@Override
		public Response invoke(IAuthenticationService authenticate,
				IAuthorizationService authorization, AuthPayload payload,
				Credentials creds) throws Exception {
			return authorization.handleDeletionRequest(payload);
		}
	},
	VERIFY {
		@Override
		public Response invoke(IAuthenticationService authenticate,
				IAuthorizationService authorization, AuthPayload payload,
				Credentials creds) throws Exception {
			
			authenticate.verify(payload);
			return null;
		}
	};
	
	public abstract Response invoke(IAuthenticationService authenticate, IAuthorizationService authorization, 
			AuthPayload payload, Credentials creds) throws Exception;
}
