package unam.mx.cella.modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import unam.mx.cella.modelo.ContenerKitMaterial;
import unam.mx.cella.modelo.PertenecerMaterialCategoria;
import unam.mx.cella.modelo.Unidadmaterial;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-11-20T16:04:07")
@StaticMetamodel(Material.class)
public class Material_ { 

    public static volatile SingularAttribute<Material, String> descripcion;
    public static volatile SingularAttribute<Material, String> nombrematerial;
    public static volatile SingularAttribute<Material, byte[]> foto;
    public static volatile CollectionAttribute<Material, PertenecerMaterialCategoria> pertenecerMaterialCategoriaCollection;
    public static volatile SingularAttribute<Material, Integer> id;
    public static volatile CollectionAttribute<Material, Unidadmaterial> unidadmaterialCollection;
    public static volatile CollectionAttribute<Material, ContenerKitMaterial> contenerKitMaterialCollection;

}