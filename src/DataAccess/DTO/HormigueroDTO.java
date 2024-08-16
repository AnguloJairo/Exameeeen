package DataAccess.DTO;

public class HormigueroDTO {
    private Integer RowNum;
    private Integer id;  // Corresponds to the 'id' field
    private String tipoHormiga;  // Corresponds to the 'tipo_hormiga' field
    private Integer sexoId;  // Corresponds to the 'sexo_id' field
    private Integer provinciaId;  // Corresponds to the 'provincia_id' field
    private Integer genoAlimentoId;  // Corresponds to the 'geno_alimento_id' field
    private Integer ingestaNativaId;  // Corresponds to the 'ingesta_nativa_id' field
    private String estadoHorm;  // Corresponds to the 'EstadoHorm' field
    private String estado;  // Corresponds to the 'Estado' field
    private String fechaCrea;  // Corresponds to the 'FechaCrea' field
    private String fechaModifica;  // Corresponds to the 'FechaModifica' field

    public HormigueroDTO() { }

    public HormigueroDTO(String tipoHormiga) {
        this.tipoHormiga = tipoHormiga;
    }

    public HormigueroDTO(Integer RowNum, int id, String tipoHormiga, Integer sexoId, Integer provinciaId, Integer genoAlimentoId, Integer ingestaNativaId, String estadoHorm, String estado, String fechaCrea, String fechaModifica) {
        this.RowNum = RowNum;
        this.id = id;
        this.tipoHormiga = tipoHormiga;
        this.sexoId = sexoId;
        this.provinciaId = provinciaId;
        this.genoAlimentoId = genoAlimentoId;
        this.ingestaNativaId = ingestaNativaId;
        this.estadoHorm = estadoHorm;
        this.estado = estado;
        this.fechaCrea = fechaCrea;
        this.fechaModifica = fechaModifica;
    }

    public Integer getRowNum() {
        return RowNum;
    }

    public void setRowNum(Integer rowNum) {
        RowNum = rowNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoHormiga() {
        return tipoHormiga;
    }

    public void setTipoHormiga(String tipoHormiga) {
        this.tipoHormiga = tipoHormiga;
    }

    public Integer getSexoId() {
        return sexoId;
    }

    public void setSexoId(Integer sexoId) {
        this.sexoId = sexoId;
    }

    public Integer getProvinciaId() {
        return provinciaId;
    }

    public void setProvinciaId(Integer provinciaId) {
        this.provinciaId = provinciaId;
    }

    public Integer getGenoAlimentoId() {
        return genoAlimentoId;
    }

    public void setGenoAlimentoId(Integer genoAlimentoId) {
        this.genoAlimentoId = genoAlimentoId;
    }

    public Integer getIngestaNativaId() {
        return ingestaNativaId;
    }

    public void setIngestaNativaId(Integer ingestaNativaId) {
        this.ingestaNativaId = ingestaNativaId;
    }

    public String getEstadoHorm() {
        return estadoHorm;
    }

    public void setEstadoHorm(String estadoHorm) {
        this.estadoHorm = estadoHorm;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaCrea() {
        return fechaCrea;
    }

    public void setFechaCrea(String fechaCrea) {
        this.fechaCrea = fechaCrea;
    }

    public String getFechaModifica() {
        return fechaModifica;
    }

    public void setFechaModifica(String fechaModifica) {
        this.fechaModifica = fechaModifica;
    }

    
    

    @Override
    public String toString() {
        return getClass().getName()
                + "\n Id:               " + getId()
                + "\n TipoHormiga:      " + getTipoHormiga()
                + "\n SexoId:           " + getSexoId()
                + "\n ProvinciaId:      " + getProvinciaId()
                + "\n GenoAlimentoId:   " + getGenoAlimentoId()
                + "\n IngestaNativaId:  " + getIngestaNativaId()
                + "\n EstadoHorm:       " + getEstadoHorm()
                + "\n Estado:           " + getEstado()
                + "\n FechaCrea:        " + getFechaCrea()
                + "\n FechaModifica:    " + getFechaModifica();
    }
}
