package com.slovers.sirgep.da;

import com.slovers.sirgep.models.ventas.Entrada;

public class EspacioCRUD{
	public void insertar(Entrada entrada) throws SQLException {
        String query = "INSERT INTO Entrada() "
                + " values(?, ?, ?, ?, ?)";
        try(Connection con = DBManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);) {        
            alumno.setActivo(true);//todo registro insertado siempre esta activado
            setAlumnoParameters(ps, alumno);
            ps.executeUpdate(); 
            //Traer el ultimo ID autogenerado
            try(Statement st = con.createStatement();
                ResultSet rskeys = st.executeQuery("select @@last_insert_id");){            
                if(rskeys.next()){
                    alumno.setId(rskeys.getInt(1));
                }
            }
        } 
    }
    
    public ArrayList<Alumno> obtenerTodos() throws SQLException {
        ArrayList<Alumno> alumnos = new ArrayList<>();
        String query = "SELECT id, nombre, fecha_nacimiento, CRAEST, activo, tipo_alumno FROM Alumno WHERE activo = 1";
        try(Connection con = DBManager.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);) {        
            while(rs.next()){
                Alumno alumno = mapAlumno(rs);
                alumnos.add(alumno);
            }
        } 
        return alumnos;
    }
    
    public Alumno obtenerPorId(int id) throws Exception {
        String sql = "SELECT id, nombre, fecha_nacimiento, CRAEST, activo, tipo_alumno FROM Alumno WHERE id=?";
        try (Connection conn = DBManager.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapAlumno(rs);
                }
            }
        }
        return null;
    }
    
    public void actualizar(Alumno alumno) throws Exception {
        String query = "UPDATE Alumno SET nombre=?, fecha_nacimiento=?, CRAEST=?, activo=?, tipo_alumno=? WHERE id=?";
        try (Connection conn = DBManager.getConnection(); 
                PreparedStatement ps = conn.prepareStatement(query)) {
            setAlumnoParameters(ps, alumno);
            ps.setInt(6, alumno.getId());
            ps.executeUpdate();
        }
    }
    
    public void eliminar(int id) throws Exception {
        //Eliminación logica
        String query = "UPDATE Alumno SET activo=0 WHERE id=?";
        try (Connection conn = DBManager.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(query)) {            
             ps.setInt(1, id);
             ps.executeUpdate();
        }
    }
    
    private void setAlumnoParameters(PreparedStatement ps, Alumno a) throws SQLException {
        ps.setString(1, a.getNombre());
        ps.setDate(2, a.getFechaNacimiento());
        //ps.setDate(2, new java.sql.Date(a.getFechaNacimiento().getTime()));// Asumiendo fecha_nacimiento como java.util.Date
        ps.setDouble(3, a.getCraest());
        ps.setBoolean(4, a.isActivo());
        ps.setString(5, a.getTipoAlumno().name());
    }
    
    private Alumno mapAlumno(ResultSet rs) throws SQLException {
        Alumno a = new Alumno();
        a.setId(rs.getInt("id"));
        a.setNombre(rs.getString("nombre"));
        a.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
        //a.setFechaNacimiento(new java.util.Date(rs.getDate("fecha_nacimiento").getTime()));// Asumiendo fecha_nacimiento como java.util.Date
        a.setCraest(rs.getDouble("CRAEST"));
        a.setActivo(rs.getBoolean("activo"));
        a.setTipoAlumno(TIPO_ALUMNO.valueOf(rs.getString("tipo_alumno")));
        return a;
    }
}