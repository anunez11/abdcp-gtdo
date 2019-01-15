package pe.gtdo.util.constante;

public enum TypeAudit {
   CREATE(1),UPDATE(2);
   private final Integer value;
	 
	 private TypeAudit(Integer value) {
       this.value = value;
    }
	 public Integer VALUE() {
       return value;
    }
}
