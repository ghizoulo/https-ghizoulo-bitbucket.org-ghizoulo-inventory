ce projet est pour la gestion d'inventaire à l'aide de la technologie RFID
=> il permet de détecter un produit et de l'ajouter, on ne peut pas ajouter une liste de produit
=> il permet de détecter le sortie d'un ou plusieurs produit en lancant un lecteur
=> il ne y'a pas un lecteur en arrière qui détecte automatiquement les tags
=> la gestion des produits est structurée selon  les familles, les depots et les stocks
=> la gestion des familles est une structure virtuelle et non pas physique
=> la gestion des utilisateurs n'est pas complete, il manque la modification, la suppression et la modification de mdp

les technologies utilisés et ses versions

Hibernate = 4.1.0.Final (avec les annotations)
Spring = 3.1.1.RELEASE
Spring Security = 3.1.1.RELEASE
JSF = 2.2.2
PrimeFaces = 5.0
MySql = 5.1.17
IDE = eclipse 
Structure MVC, 5 couches(model, dao, service, presenation et security)

les fichiers de configurations:
	=>spring-security.xml
	=>applicationContext.xml
	=>faces-config.xml
	=>web.xml

L'application est divisé en 2 espaces, le 1er est pour l'utilisateur normal et le 2eme est pour l'admin

Admin => mkyong
	mdp =>123456

User => alex
	mdp =>123456