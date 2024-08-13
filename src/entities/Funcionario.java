package entities;

public class Funcionario {
    //Atributos
    private int idFuncionario;
    private String nomeFuncionario;
    private String cargoFuncionario;
    private Double salarioFuncionario;

    //Construtor
    public Funcionario() {
    }

    public Funcionario(int idFuncionario, String nomeFuncionario, String cargoFuncionario, Double salarioFuncionario) {
        this.idFuncionario = idFuncionario;
        this.nomeFuncionario = nomeFuncionario;
        this.cargoFuncionario = cargoFuncionario;
        this.salarioFuncionario = salarioFuncionario;
    }

    //Getters
    public int getIdFuncionario() {
        return idFuncionario;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public String getCargoFuncionario() {
        return cargoFuncionario;
    }

    public Double getSalarioFuncionario() {
        return salarioFuncionario;
    }

    //Setters
    public void setCargoFuncionario(String cargoFuncionario) {
        this.cargoFuncionario = cargoFuncionario;
    }

    public void setSalarioFuncionario(Double salarioFuncionario) {
        this.salarioFuncionario = salarioFuncionario;
    }

    //Métodos
    @Override
    public String toString() {
        return "" + this.getIdFuncionario() + ";" +
                this.getNomeFuncionario() + ";" +
                this.getCargoFuncionario() + ";" +
                this.getSalarioFuncionario();
    }   
}
