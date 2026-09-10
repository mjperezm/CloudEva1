# 🔐 GUÍA AZURE AD - Pedidos360

## PASO 1: Registrar Frontend

1. https://portal.azure.com → Azure Active Directory
2. App registrations → New registration
3. Nombre: "Pedidos360 Frontend"
4. Redirect URI: Single-page application → http://localhost:4200
5. **Guardar: FRONTEND_CLIENT_ID y TENANT_ID**

## PASO 2: Registrar Backend

1. New registration
2. Nombre: "Pedidos360 Backend"
3. Redirect URI: (dejar vacío)
4. Ir a "Expose an API"
5. Application ID URI: api://BACKEND_CLIENT_ID
6. Add scope: "Orders.ReadWrite"
7. Ve a "Certificates & secrets"
8. New client secret (12 meses)
9. **Guardar: BACKEND_CLIENT_ID, BACKEND_CLIENT_SECRET**

## PASO 3: Conectar Permisos

1. Ve a Frontend app
2. API permissions → Add a permission
3. My APIs → Pedidos360 Backend
4. Selecciona: Orders.ReadWrite
5. Grant admin consent

## PASO 4: Actualizar Código

**frontend/pedidos360-app/src/app/auth.config.ts:**
```typescript
const clientId = 'FRONTEND_CLIENT_ID';
const tenantId = 'TENANT_ID';
```

**backend/src/main/resources/application.yml:**
```yaml
spring.security.oauth2.resourceserver.jwt.issuer-uri: 
  https://login.microsoftonline.com/TENANT_ID/v2.0
```

**.env.prod:**
```bash
AZURE_TENANT_ID=TENANT_ID
AZURE_CLIENT_ID=BACKEND_CLIENT_ID
AZURE_CLIENT_SECRET=BACKEND_CLIENT_SECRET
```

## PASO 5: Probar

```bash
docker-compose up -d
# Frontend: http://localhost:4200
# Click "Iniciar Sesión"
```

Ver guía completa en el archivo original.
