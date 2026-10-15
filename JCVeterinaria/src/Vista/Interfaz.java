package Vista;

import Servidor.dao.*;
import Dominio.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.EventObject;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.plaf.ScrollBarUI;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import org.w3c.dom.events.MouseEvent;

import net.miginfocom.swing.MigLayout;

public class Interfaz extends JFrame {

	private gestionDueño duenoDAO = new gestionDueño();
	private gestionMascota mascotaDAO = new gestionMascota();
	private gestionVacuna vacunaDAO = new gestionVacuna();
	private gestionVeterinario veterinarioDAO = new gestionVeterinario();
	private gestionConsulta consultaDAO = new gestionConsulta();

	private static final Color FONDO = new Color(30, 32, 36); // Fondo general del tema oscuro.
	private static final Color PANEL = new Color(38, 41, 46); // Fondo de paneles y pestañas oscuras.
	private static final Color BORDE = new Color(65, 69, 77); // Bordes de la barra de menú oscura.
	private static final Color TEXTO = new Color(224, 228, 235); // Texto principal del tema oscuro.
	private static final Color ACENTO_BLANCO = new Color(220, 220, 220); // Color de la pestaña activa oscura.
	private static final Color ACENTO_NEGRO = new Color(60, 60, 60); // Color de la pestaña activa clara.
    private static final Color RESALTADO = new Color(58, 61, 67); // Selección de pestañas y menús oscuros.
	private static final Color FONDO_CLARO = new Color(242, 243, 245); // Fondo general del tema claro.
	private static final Color PANEL_CLARO = new Color(232, 234, 238); // Fondo de paneles y pestañas claras.
	private static final Color BORDE_CLARO = new Color(190, 194, 201); // Bordes de la barra de menú clara.
	private static final Color TEXTO_CLARO = new Color(35, 38, 43); // Texto principal del tema claro.
	private static final Color RESALTADO_CLARO = new Color(200, 200, 200); // Selección de pestañas y menús claros.
	private static final Dimension DIMENSION_PANEL_REGISTRO = new Dimension(500, 300);
	
	
	
	private boolean temaOscuro = true;
	private JTabbedPane pestañas;
	private JPanel panelRegistrar;
	private JPanel panelModificar;
	private JMenuBar barraDeMenu;
	private JMenuBar barraDeMenuModificar;
	private JPanel panelesModificar;
	private CardLayout tarjetasModificar;
	private final Map<String, DefaultListModel<String>> modelosModificar = new HashMap<>();
	private final Map<String, JList<String>> listasModificar = new HashMap<>();
	private final Map<String, List<String>> datosModificar = new HashMap<>();
	private final Map<String, JTextField> buscadoresModificar = new HashMap<>();
	private final Map<String, JTextField> idsModificar = new HashMap<>();
	private final Map<String, List<JTextField>> camposModificar = new HashMap<>();
    private JLabel tituloReg;
	private JPanel dueño;
	private JPanel mascota;
	private JPanel vacuna;
	private JPanel consulta;
	private JPanel veterinario;
	private JLabel nombreLabel;
	private JLabel eMailLabel;
	private JLabel telefonoLabel;
	private JLabel CILabel;
	private JTextField nombreField;
	private JTextField eMailField;
	private JTextField telefonoField;
	private JTextField CIField;
	private JButton regDueñoButton;
	private JLabel nombreMascotaLabel;
	private JLabel edadMascotaLabel;
	private JLabel especieMascotaLabel;
	private JLabel razaMascotaLabel;
	private JLabel sexoMascotaLabel;
	private JLabel pesoMascotaLabel;
	private JTextField nombreMascotaField;
	private JTextField edadMascotaField;
	private JTextField especieMascotaField;
	private JTextField razaMascotaField;
	private JTextField sexoMascotaField;
	private JTextField pesoMascotaField;
	private JButton regMascotaButton;
	private JLabel procedimientoLabel;
	private JLabel fechaConsultaLabel;
	private JLabel horaConsultaLabel;
	private JLabel idMascotaLabel;
	private JLabel idVeterinarioLabel;
	private JTextField procedimientoField;
	private JTextField fechaConsultaField;
	private JTextField horaConsultaField;
	private JTextField idMascotaField;
	private JTextField idVeterinarioField;
	private JButton regConsultaButton;
	private JLabel tipoVacunaLabel;
	private JLabel fechaVacunaLabel;
	private JLabel horaVacunaLabel;
	private JLabel vencimientoLabel;
	private JLabel idMascotaVacunaLabel;
	private JLabel idConsultaLabel;
	private JTextField tipoVacunaField;
	private JTextField fechaVacunaField;
	private JTextField horaVacunaField;
	private JTextField vencimientoField;
	private JTextField idMascotaVacunaField;
	private JTextField idConsultaField;
	private JButton regVacunaButton;
	private JLabel nombreVeterinarioLabel;
	private JLabel contraseñaVeterinarioLabel;
	private JLabel especialidadLabel;
	private JTextField nombreVeterinarioField;
	private JTextField contraseñaVeterinarioField;
	private JTextField especialidadField;
	private JButton regVeterinarioButton;
	private JButton delDueño;
	private JButton delMascota;
	private JButton delVacuna;
	private JButton delConsulta;
	private JButton delVeterinario;

	private JTextField buscadorDueño;
	private JTextField buscadorMascota;
	private JTextField buscadorVacuna;
	private JTextField buscadorConsulta;
	private JTextField buscadorVeterinario;

	private JList<String> listaDueños;
	private JList<String> listaMascotas;
	private JList<String> listaVacunas;
	private JList<String> listaConsultas;
	private JList<String> listaVeterinarios;

	private DefaultListModel<String> modeloDueños;
	private DefaultListModel<String> modeloMascotas;
	private DefaultListModel<String> modeloVacunas;
	private DefaultListModel<String> modeloConsultas;
	private DefaultListModel<String> modeloVeterinarios;

 
	public Interfaz() {
		super("JCVeterinaria");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(720, 440));
		setSize(860, 520);
		setLocationRelativeTo(null);
		getContentPane().setBackground(FONDO);

		setLayout(new MigLayout("fill, insets 0", "[grow]", "[grow]"));
		add(crearPestañas(), "grow");
        initComponents();
		actualizarTema();

	}

    private void initComponents() {

// Inicializar los modelos de las listas
		modeloDueños = new DefaultListModel<>();
		modeloMascotas = new DefaultListModel<>();
		modeloVacunas = new DefaultListModel<>();
		modeloConsultas = new DefaultListModel<>();
		modeloVeterinarios = new DefaultListModel<>();

// Crear JLists para cada modelo
		listaDueños = new JList<>(modeloDueños);
		listaMascotas = new JList<>(modeloMascotas);
		listaVacunas = new JList<>(modeloVacunas);
		listaConsultas = new JList<>(modeloConsultas);
		listaVeterinarios = new JList<>(modeloVeterinarios);

// Crear JScrollPanes para cada JList
		JScrollPane dueñoRegList = new JScrollPane(listaDueños);
		JScrollPane mascotaRegList = new JScrollPane(listaMascotas);
		JScrollPane consultaRegList = new JScrollPane(listaConsultas);
		JScrollPane vacunaRegList = new JScrollPane(listaVacunas);
		JScrollPane veterinarioRegList = new JScrollPane(listaVeterinarios);


        tituloReg = new JLabel("Registrar");
        tituloReg.setFont(new Font("Segoe UI", Font.BOLD, 30));
        tituloReg.setForeground(TEXTO);

// Botones para eliminar
		delDueño = new JButton("Eliminar dueño");
		delDueño.setFont(new Font("Segoe UI", Font.BOLD, 18));
		delDueño.setBackground(RESALTADO);
		delDueño.setForeground(TEXTO_CLARO);
		delDueño.addActionListener(e -> {
			eliminar("Dueño");
		});

		delMascota = new JButton("Eliminar mascota");
		delMascota.setFont(new Font("Segoe UI", Font.BOLD, 18));
		delMascota.setBackground(RESALTADO);
		delMascota.setForeground(TEXTO_CLARO);
		delMascota.addActionListener(e -> {
			eliminar("Mascota");
		});

		delVacuna = new JButton("Eliminar vacuna");
		delVacuna.setFont(new Font("Segoe UI", Font.BOLD, 18));
		delVacuna.setBackground(RESALTADO);
		delVacuna.setForeground(TEXTO_CLARO);
		delVacuna.addActionListener(e -> {
			eliminar("Vacuna");
		});
		
		delConsulta = new JButton("Eliminar consulta");
		delConsulta.setFont(new Font("Segoe UI", Font.BOLD, 18));
		delConsulta.setBackground(RESALTADO);
		delConsulta.setForeground(TEXTO_CLARO);
		delConsulta.addActionListener(e -> {
			eliminar("Consulta");
		});

		delVeterinario = new JButton("Eliminar veterinario");
		delVeterinario.setFont(new Font("Segoe UI", Font.BOLD, 18));
		delVeterinario.setBackground(RESALTADO);
		delVeterinario.setForeground(TEXTO_CLARO);
		delVeterinario.addActionListener(e -> {
			eliminar("Veterinario");
		});

// Buscador de registros
		buscadorDueño = new JTextField(28);
		buscadorDueño.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		buscadorDueño.setBackground(PANEL);
		buscadorDueño.setForeground(TEXTO);
		buscadorMascota = new JTextField(28);
		buscadorMascota.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		buscadorMascota.setBackground(PANEL);
		buscadorMascota.setForeground(TEXTO);
		buscadorVacuna = new JTextField(28);
		buscadorVacuna.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		buscadorVacuna.setBackground(PANEL);
		buscadorVacuna.setForeground(TEXTO);
		buscadorConsulta = new JTextField(28);
		buscadorConsulta.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		buscadorConsulta.setBackground(PANEL);
		buscadorConsulta.setForeground(TEXTO);
		buscadorVeterinario = new JTextField(28);
		buscadorVeterinario.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		buscadorVeterinario.setBackground(PANEL);
		buscadorVeterinario.setForeground(TEXTO);

// Paneles de registro

		dueño = new JPanel(new MigLayout("fill, insets 0", "[grow]", "[] [grow]"));
		dueño.setBackground(PANEL);
		fijarDimensionPanel(dueño);

		mascota = new JPanel(new MigLayout("fill, insets 0", "[grow]", "[] [grow]"));
		mascota.setBackground(PANEL);
		fijarDimensionPanel(mascota);

		consulta = new JPanel(new MigLayout("fill, insets 0", "[grow]", "[] [grow]"));
		consulta.setBackground(PANEL);
		fijarDimensionPanel(consulta);

		veterinario = new JPanel(new MigLayout("fill, insets 0", "[grow]", "[] [grow]"));
		veterinario.setBackground(PANEL);
		fijarDimensionPanel(veterinario);

		vacuna = new JPanel(new MigLayout("fill, insets 0", "[grow]", "[] [grow]"));
		vacuna.setBackground(PANEL);
		fijarDimensionPanel(vacuna);

		nombreLabel = new JLabel("Nombre:");
		nombreLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		nombreLabel.setForeground(TEXTO);
		nombreField = new JTextField(20);
		nombreField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		nombreField.setBackground(PANEL);
		nombreField.setForeground(TEXTO);

		eMailLabel = new JLabel("e-Mail:");
		eMailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		eMailLabel.setForeground(TEXTO);
		eMailField = new JTextField(20);
		eMailField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		eMailField.setBackground(PANEL);
		eMailField.setForeground(TEXTO);

		telefonoLabel = new JLabel("Teléfono:");
		telefonoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		telefonoLabel.setForeground(TEXTO);
		telefonoField = new JTextField(20);
		telefonoField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		telefonoField.setBackground(PANEL);
		telefonoField.setForeground(TEXTO);

		CILabel = new JLabel("CI:");
		CILabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		CILabel.setForeground(TEXTO);
		CIField = new JTextField(20);
		CIField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		CIField.setBackground(PANEL);
		CIField.setForeground(TEXTO);

		regDueñoButton = new JButton("Registrar Dueño");
		regDueñoButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
		regDueñoButton.setBackground(RESALTADO);
		regDueñoButton.setForeground(TEXTO);
		regDueñoButton.addActionListener(e -> {
			registrar("Dueño");
		});

		nombreMascotaLabel = new JLabel("Nombre:");
		nombreMascotaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		nombreMascotaLabel.setForeground(TEXTO);
		nombreMascotaField = new JTextField(20);
		nombreMascotaField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		nombreMascotaField.setBackground(PANEL);
		nombreMascotaField.setForeground(TEXTO);

		edadMascotaLabel = new JLabel("Edad:");
		edadMascotaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		edadMascotaLabel.setForeground(TEXTO);
		edadMascotaField = new JTextField(20);
		edadMascotaField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		edadMascotaField.setBackground(PANEL);
		edadMascotaField.setForeground(TEXTO);

		especieMascotaLabel = new JLabel("Especie:");
		especieMascotaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		especieMascotaLabel.setForeground(TEXTO);
		especieMascotaField = new JTextField(20);
		especieMascotaField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		especieMascotaField.setBackground(PANEL);
		especieMascotaField.setForeground(TEXTO);

		razaMascotaLabel = new JLabel("Raza:");
		razaMascotaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		razaMascotaLabel.setForeground(TEXTO);
		razaMascotaField = new JTextField(20);
		razaMascotaField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		razaMascotaField.setBackground(PANEL);
		razaMascotaField.setForeground(TEXTO);

		sexoMascotaLabel = new JLabel("Sexo:");
		sexoMascotaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		sexoMascotaLabel.setForeground(TEXTO);
		sexoMascotaField = new JTextField(20);
		sexoMascotaField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		sexoMascotaField.setBackground(PANEL);
		sexoMascotaField.setForeground(TEXTO);

		pesoMascotaLabel = new JLabel("Peso:");
		pesoMascotaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		pesoMascotaLabel.setForeground(TEXTO);
		pesoMascotaField = new JTextField(20);
		pesoMascotaField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		pesoMascotaField.setBackground(PANEL);
		pesoMascotaField.setForeground(TEXTO);

		regMascotaButton = new JButton("Registrar Mascota");
		regMascotaButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
		regMascotaButton.setBackground(RESALTADO);
		regMascotaButton.setForeground(TEXTO);
		regMascotaButton.addActionListener(e -> {
			registrar("Mascota");
		});

		procedimientoLabel = new JLabel("Procedimiento:");
		procedimientoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		procedimientoLabel.setForeground(TEXTO);
		procedimientoField = new JTextField(20);
		procedimientoField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		procedimientoField.setBackground(PANEL);
		procedimientoField.setForeground(TEXTO);

		fechaConsultaLabel = new JLabel("Fecha de consulta:");
		fechaConsultaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		fechaConsultaLabel.setForeground(TEXTO);
		fechaConsultaField = new JTextField(20);
		fechaConsultaField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		fechaConsultaField.setBackground(PANEL);
		fechaConsultaField.setForeground(TEXTO);

		horaConsultaLabel = new JLabel("Hora:");
		horaConsultaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		horaConsultaLabel.setForeground(TEXTO);
		horaConsultaField = new JTextField(20);
		horaConsultaField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		horaConsultaField.setBackground(PANEL);
		horaConsultaField.setForeground(TEXTO);

		idMascotaLabel = new JLabel("ID de mascota:");
		idMascotaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		idMascotaLabel.setForeground(TEXTO);
		idMascotaField = new JTextField(20);
		idMascotaField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		idMascotaField.setBackground(PANEL);
		idMascotaField.setForeground(TEXTO);

		idVeterinarioLabel = new JLabel("ID de veterinario:");
		idVeterinarioLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		idVeterinarioLabel.setForeground(TEXTO);
		idVeterinarioField = new JTextField(20);
		idVeterinarioField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		idVeterinarioField.setBackground(PANEL);
		idVeterinarioField.setForeground(TEXTO);

		regConsultaButton = new JButton("Registrar Consulta");
		regConsultaButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
		regConsultaButton.setBackground(RESALTADO);
		regConsultaButton.setForeground(TEXTO);
		regConsultaButton.addActionListener(e -> {
			registrar("Consulta");
		});

		tipoVacunaLabel = new JLabel("Tipo de vacuna:");
		tipoVacunaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		tipoVacunaLabel.setForeground(TEXTO);
		tipoVacunaField = new JTextField(20);
		tipoVacunaField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		tipoVacunaField.setBackground(PANEL);
		tipoVacunaField.setForeground(TEXTO);

		fechaVacunaLabel = new JLabel("Fecha de vacuna:");
		fechaVacunaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		fechaVacunaLabel.setForeground(TEXTO);
		fechaVacunaField = new JTextField(20);
		fechaVacunaField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		fechaVacunaField.setBackground(PANEL);
		fechaVacunaField.setForeground(TEXTO);

		horaVacunaLabel = new JLabel("Hora de vacuna:");
		horaVacunaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		horaVacunaLabel.setForeground(TEXTO);
		horaVacunaField = new JTextField(20);
		horaVacunaField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		horaVacunaField.setBackground(PANEL);
		horaVacunaField.setForeground(TEXTO);

		vencimientoLabel = new JLabel("Vencimiento:");
		vencimientoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		vencimientoLabel.setForeground(TEXTO);
		vencimientoField = new JTextField(20);
		vencimientoField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		vencimientoField.setBackground(PANEL);
		vencimientoField.setForeground(TEXTO);

		idMascotaVacunaLabel = new JLabel("ID de mascota:");
		idMascotaVacunaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		idMascotaVacunaLabel.setForeground(TEXTO);
		idMascotaVacunaField = new JTextField(20);
		idMascotaVacunaField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		idMascotaVacunaField.setBackground(PANEL);
		idMascotaVacunaField.setForeground(TEXTO);

		idConsultaLabel = new JLabel("ID de consulta:");
		idConsultaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		idConsultaLabel.setForeground(TEXTO);
		idConsultaField = new JTextField(20);
		idConsultaField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		idConsultaField.setBackground(PANEL);
		idConsultaField.setForeground(TEXTO);

		regVacunaButton = new JButton("Registrar Vacuna");
		regVacunaButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
		regVacunaButton.setBackground(RESALTADO);
		regVacunaButton.setForeground(TEXTO);
		regVacunaButton.addActionListener(e -> {
			registrar("Vacuna");
		});

		nombreVeterinarioLabel = new JLabel("Nombre:");
		nombreVeterinarioLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		nombreVeterinarioLabel.setForeground(TEXTO);
		nombreVeterinarioField = new JTextField(20);
		nombreVeterinarioField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		nombreVeterinarioField.setBackground(PANEL);
		nombreVeterinarioField.setForeground(TEXTO);

		contraseñaVeterinarioLabel = new JLabel("Contraseña:");
		contraseñaVeterinarioLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		contraseñaVeterinarioLabel.setForeground(TEXTO);
		contraseñaVeterinarioField = new JTextField(20);
		contraseñaVeterinarioField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		contraseñaVeterinarioField.setBackground(PANEL);
		contraseñaVeterinarioField.setForeground(TEXTO);

		especialidadLabel = new JLabel("Especialidad:");
		especialidadLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		especialidadLabel.setForeground(TEXTO);
		especialidadField = new JTextField(20);
		especialidadField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		especialidadField.setBackground(PANEL);
		especialidadField.setForeground(TEXTO);

		regVeterinarioButton = new JButton("Registrar Veterinario");
		regVeterinarioButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
		regVeterinarioButton.setBackground(RESALTADO);
		regVeterinarioButton.setForeground(TEXTO);
		regVeterinarioButton.addActionListener(e -> {
			registrar("Veterinario");
		});

		dueño.add(nombreLabel, "pos 0.05al 0.1al, wrap");
		dueño.add(nombreField, "pos 0.25al 0.1al, wrap");
		dueño.add(eMailLabel, "pos 0.05al 0.2al, wrap");
		dueño.add(eMailField, "pos 0.25al 0.2al, wrap");
		dueño.add(telefonoLabel, "pos 0.05al 0.3al, wrap");
		dueño.add(telefonoField, "pos 0.25al 0.3al, wrap");
		dueño.add(CILabel, "pos 0.05al 0.4al, wrap");
		dueño.add(CIField, "pos 0.25al 0.4al, wrap");
		dueño.add(regDueñoButton, "pos 0.1al 0.9al, wrap");
		dueño.add(delDueño, "pos 0.3al 0.9al, wrap");

		mascota.add(nombreMascotaLabel, "pos 0.05al 0.1al, wrap");
		mascota.add(nombreMascotaField, "pos 0.25al 0.1al, wrap");
		mascota.add(edadMascotaLabel, "pos 0.05al 0.2al, wrap");
		mascota.add(edadMascotaField, "pos 0.25al 0.2al, wrap");
		mascota.add(especieMascotaLabel, "pos 0.05al 0.3al, wrap");
		mascota.add(especieMascotaField, "pos 0.25al 0.3al, wrap");
		mascota.add(razaMascotaLabel, "pos 0.05al 0.4al, wrap");
		mascota.add(razaMascotaField, "pos 0.25al 0.4al, wrap");
		mascota.add(sexoMascotaLabel, "pos 0.05al 0.5al, wrap");
		mascota.add(sexoMascotaField, "pos 0.25al 0.5al, wrap");
		mascota.add(pesoMascotaLabel, "pos 0.05al 0.6al, wrap");
		mascota.add(pesoMascotaField, "pos 0.25al 0.6al, wrap");
		mascota.add(regMascotaButton, "pos 0.1al 0.9al, wrap");
		mascota.add(delMascota, "pos 0.3al 0.9al, wrap");

		consulta.add(procedimientoLabel, "pos 0.05al 0.1al, wrap");
		consulta.add(procedimientoField, "pos 0.25al 0.1al, wrap");
		consulta.add(fechaConsultaLabel, "pos 0.05al 0.2al, wrap");
		consulta.add(fechaConsultaField, "pos 0.25al 0.2al, wrap");
		consulta.add(horaConsultaLabel, "pos 0.05al 0.3al, wrap");
		consulta.add(horaConsultaField, "pos 0.25al 0.3al, wrap");
		consulta.add(idMascotaLabel, "pos 0.05al 0.4al, wrap");
		consulta.add(idMascotaField, "pos 0.25al 0.4al, wrap");
		consulta.add(idVeterinarioLabel, "pos 0.05al 0.5al, wrap");
		consulta.add(idVeterinarioField, "pos 0.25al 0.5al, wrap");
		consulta.add(regConsultaButton, "pos 0.1al 0.9al, wrap");
		consulta.add(delConsulta, "pos 0.3al 0.9al, wrap");

		vacuna.add(tipoVacunaLabel, "pos 0.05al 0.1al, wrap");
		vacuna.add(tipoVacunaField, "pos 0.25al 0.1al, wrap");
		vacuna.add(fechaVacunaLabel, "pos 0.05al 0.2al, wrap");
		vacuna.add(fechaVacunaField, "pos 0.25al 0.2al, wrap");
		vacuna.add(horaVacunaLabel, "pos 0.05al 0.3al, wrap");
		vacuna.add(horaVacunaField, "pos 0.25al 0.3al, wrap");
		vacuna.add(vencimientoLabel, "pos 0.05al 0.4al, wrap");
		vacuna.add(vencimientoField, "pos 0.25al 0.4al, wrap");
		vacuna.add(idMascotaVacunaLabel, "pos 0.05al 0.5al, wrap");
		vacuna.add(idMascotaVacunaField, "pos 0.25al 0.5al, wrap");
		vacuna.add(idConsultaLabel, "pos 0.05al 0.6al, wrap");
		vacuna.add(idConsultaField, "pos 0.25al 0.6al, wrap");
		vacuna.add(regVacunaButton, "pos 0.1al 0.9al, wrap");
		vacuna.add(delVacuna, "pos 0.3al 0.9al, wrap");

		veterinario.add(nombreVeterinarioLabel, "pos 0.05al 0.1al, wrap");
		veterinario.add(nombreVeterinarioField, "pos 0.25al 0.1al, wrap");
		veterinario.add(contraseñaVeterinarioLabel, "pos 0.05al 0.2al, wrap");
		veterinario.add(contraseñaVeterinarioField, "pos 0.25al 0.2al, wrap");
		veterinario.add(especialidadLabel, "pos 0.05al 0.3al, wrap");
		veterinario.add(especialidadField, "pos 0.25al 0.3al, wrap");
		veterinario.add(regVeterinarioButton, "pos 0.1al 0.9al, wrap");
		veterinario.add(delVeterinario, "pos 0.3al 0.9al, wrap");

		panelRegistrar.add(mascota, "w 1200!, h 450!, pos 0.1al 0.5al, wrap");
		panelRegistrar.add(consulta, "w 1200!, h 450!, pos 0.1al 0.5al, wrap");
		panelRegistrar.add(veterinario, "w 1200!, h 450!, pos 0.1al 0.5al, wrap");
		panelRegistrar.add(vacuna, "w 1200!, h 450!, pos 0.1al 0.5al, wrap");
		panelRegistrar.add(dueño, "w 1200!, h 450!, pos 0.1al 0.5al, wrap");
		panelRegistrar.add(tituloReg, "pos 0.5al 0.1al");
		
		dueño.add(buscadorDueño, "pos 0.95al 0.05al, wrap");
		mascota.add(buscadorMascota, "pos 0.95al 0.05al, wrap");
		consulta.add(buscadorConsulta, "pos 0.95al 0.05al, wrap");
		vacuna.add(buscadorVacuna, "pos 0.95al 0.05al, wrap");
		veterinario.add(buscadorVeterinario, "pos 0.95al 0.05al, wrap");
		
		dueño.add(dueñoRegList, "w 450!, h 380!, pos 0.95al 0.9al, wrap");
		mascota.add(mascotaRegList, "w 450!, h 380!, pos 0.95al 0.9al, wrap");
		consulta.add(consultaRegList, "w 450!, h 380!, pos 0.95al 0.9al, wrap");
		vacuna.add(vacunaRegList, "w 450!, h 380!, pos 0.95al 0.9al, wrap");
		veterinario.add(veterinarioRegList, "w 450!, h 380!, pos 0.95al 0.9al, wrap");

		dueño.setVisible(false);
		mascota.setVisible(false);
		consulta.setVisible(false);
		vacuna.setVisible(false);
		veterinario.setVisible(false);

		inicializarPanelModificar();
		actualizarTabla("Dueño");
		actualizarTabla("Mascota");
		actualizarTabla("Consulta");
		actualizarTabla("Vacuna");
		actualizarTabla("Veterinario");

		vincularBuscador(buscadorDueño, listaDueños,modeloDueños);
		vincularBuscador(buscadorMascota, listaMascotas, modeloMascotas);
		vincularBuscador(buscadorConsulta, listaConsultas, modeloConsultas);
		vincularBuscador(buscadorVacuna, listaVacunas, modeloVacunas);
		vincularBuscador(buscadorVeterinario, listaVeterinarios, modeloVeterinarios);
    }

	private void inicializarPanelModificar() {
		tarjetasModificar = new CardLayout();
		panelesModificar = new JPanel(tarjetasModificar);
		panelesModificar.setOpaque(false);

		String[] entidades = { "Dueño", "Mascota", "Vacuna", "Consulta", "Veterinario" };
		for (String entidad : entidades) {
			DefaultListModel<String> modelo = new DefaultListModel<>();
			JList<String> lista = new JList<>(modelo);
			JTextField buscador = new JTextField(28);
			JTextField id = crearCampoModificar();
			List<JTextField> campos = new ArrayList<>();
			String[] etiquetas = etiquetasModificar(entidad);
			JPanel formulario = new JPanel(new MigLayout("fillx, insets 12", "[right] [grow, fill]", "[]"));
			formulario.setOpaque(false);

			id.setEditable(false);
			formulario.add(new JLabel("ID:"), "gapbottom 8");
			formulario.add(id, "growx, wrap");
			for (String etiqueta : etiquetas) {
				JTextField campo = crearCampoModificar();
				campos.add(campo);
				formulario.add(new JLabel(etiqueta + ":"), "gapbottom 8");
				formulario.add(campo, "growx, wrap");
			}

			JButton botonModificar = new JButton("Guardar cambios");
			botonModificar.setFont(new Font("Segoe UI", Font.BOLD, 18));
			botonModificar.addActionListener(evento -> modificarRegistro(entidad));
			formulario.add(botonModificar, "span 2, center, gaptop 16");

			JPanel panelFormulario = new JPanel(new MigLayout("fill, insets 8", "[grow]", "[] [grow]"));
			panelFormulario.setOpaque(false);
			JLabel titulo = new JLabel("Modificar " + entidad);
			titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
			panelFormulario.add(titulo, "wrap");
			panelFormulario.add(formulario, "growx, top");

			JPanel panelLista = new JPanel(new MigLayout("fill, insets 8", "[grow]", "[] [grow]"));
			panelLista.setOpaque(false);
			buscador.setFont(new Font("Segoe UI", Font.PLAIN, 18));
			panelLista.add(buscador, "growx, wrap");
			JScrollPane scroll = new JScrollPane(lista);
			panelLista.add(scroll, "grow");

			JPanel panelEntidad = new JPanel(new MigLayout("fill, insets 0", "[grow 1][grow 1]", "[grow]"));
			panelEntidad.setOpaque(false);
			panelEntidad.add(panelFormulario, "grow");
			panelEntidad.add(panelLista, "grow");
			panelesModificar.add(panelEntidad, entidad);

			modelosModificar.put(entidad, modelo);
			listasModificar.put(entidad, lista);
			datosModificar.put(entidad, new ArrayList<>());
			buscadoresModificar.put(entidad, buscador);
			idsModificar.put(entidad, id);
			camposModificar.put(entidad, campos);
			lista.addListSelectionListener(evento -> {
				if (!evento.getValueIsAdjusting()) {
					cargarRegistroModificar(entidad);
				}
			});
			vincularBuscadorModificar(buscador, entidad);
		}

		panelModificar.add(panelesModificar, "grow");
		tarjetasModificar.show(panelesModificar, entidades[0]);
	}

	private JTextField crearCampoModificar() {
		JTextField campo = new JTextField(20);
		campo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		campo.setBackground(PANEL);
		campo.setForeground(TEXTO);
		return campo;
	}

	private String[] etiquetasModificar(String entidad) {
		switch (entidad) {
		case "Dueño":
			return new String[] { "Nombre", "e-Mail", "Teléfono", "CI" };
		case "Mascota":
			return new String[] { "Nombre", "Edad", "Especie", "Raza", "Sexo", "Peso" };
		case "Vacuna":
			return new String[] { "Tipo de vacuna", "Fecha de vacuna", "Hora de vacuna", "Vencimiento",
					"ID de mascota", "ID de consulta" };
		case "Consulta":
			return new String[] { "Procedimiento", "Fecha de consulta", "Hora", "ID de mascota",
					"ID de veterinario" };
		case "Veterinario":
			return new String[] { "Nombre", "Contraseña", "Especialidad" };
		default:
			throw new IllegalArgumentException("Entidad de modificación desconocida: " + entidad);
		}
	}

	private void vincularBuscadorModificar(JTextField buscador, String entidad) {
		buscador.getDocument().addDocumentListener(new DocumentListener() {
			private void filtrar() {
				String termino = buscador.getText().trim().toLowerCase();
				DefaultListModel<String> modelo = modelosModificar.get(entidad);
				modelo.clear();
				for (String fila : datosModificar.get(entidad)) {
					if (fila.toLowerCase().contains(termino)) {
						modelo.addElement(fila);
					}
				}
			}

			@Override
			public void insertUpdate(DocumentEvent evento) {
				filtrar();
			}

			@Override
			public void removeUpdate(DocumentEvent evento) {
				filtrar();
			}

			@Override
			public void changedUpdate(DocumentEvent evento) {
				filtrar();
			}
		});
	}

	private void limpiarListaModificar(String entidad) {
		List<String> datos = datosModificar.get(entidad);
		DefaultListModel<String> modelo = modelosModificar.get(entidad);
		if (datos != null && modelo != null) {
			datos.clear();
			modelo.clear();
		}
	}

	private void agregarFilaModificar(String entidad, String fila) {
		List<String> datos = datosModificar.get(entidad);
		if (datos != null) {
			datos.add(fila);
			String termino = buscadoresModificar.get(entidad).getText().trim().toLowerCase();
			if (fila.toLowerCase().contains(termino)) {
				modelosModificar.get(entidad).addElement(fila);
			}
		}
	}

	private void cargarRegistroModificar(String entidad) {
		String seleccion = listasModificar.get(entidad).getSelectedValue();
		if (seleccion == null) {
			limpiarFormularioModificar(entidad);
			return;
		}

		int id = extraerId(seleccion);
		List<JTextField> campos = camposModificar.get(entidad);
		idsModificar.get(entidad).setText(String.valueOf(id));
		switch (entidad) {
		case "Dueño":
			for (dueno registro : duenoDAO.listar()) {
				if (registro.getId_dueño() == id) {
					establecerValores(campos, registro.getNombre(), registro.getEmail(), registro.getTelefono(),
							registro.getC_I());
					return;
				}
			}
			break;
		case "Mascota":
			for (mascota registro : mascotaDAO.listar()) {
				if (registro.getId_mascota() == id) {
					establecerValores(campos, registro.getNombre(), String.valueOf(registro.getEdad()),
							registro.getEspecie(), registro.getRaza(), registro.getSexo(),
							String.valueOf(registro.getPeso()));
					return;
				}
			}
			break;
		case "Vacuna":
			for (vacuna registro : vacunaDAO.listar()) {
				if (registro.getId_vacuna() == id) {
					establecerValores(campos, registro.getTipo_vacuna(), registro.getFecha_vacuna(),
							registro.getHora_vacuna(), registro.getVencimiento(),
							String.valueOf(registro.getId_mascota()), String.valueOf(registro.getId_consulta()));
					return;
				}
			}
			break;
		case "Consulta":
			for (consulta registro : consultaDAO.listar()) {
				if (registro.getId_consulta() == id) {
					establecerValores(campos, registro.getProcedimiento(), registro.getFecha_consulta(),
							registro.getHora(), String.valueOf(registro.getId_mascota()),
							String.valueOf(registro.getId_veterinario()));
					return;
				}
			}
			break;
		case "Veterinario":
			for (veterinario registro : veterinarioDAO.listar()) {
				if (registro.getId_veterinario() == id) {
					establecerValores(campos, registro.getNombre_veterinario(), registro.getContraseña(),
							registro.getEspecialidad());
					return;
				}
			}
			break;
		default:
			throw new IllegalArgumentException("Entidad de modificación desconocida: " + entidad);
		}
		limpiarFormularioModificar(entidad);
		mostrarErrorModificar("No se encontró el registro seleccionado. Actualiza la lista e inténtalo otra vez.");
	}

	private int extraerId(String fila) {
		int finId = fila.indexOf('|');
		if (finId < 0) {
			throw new IllegalArgumentException("Formato de registro no válido: " + fila);
		}
		return Integer.parseInt(fila.substring(0, finId).replace("ID:", "").trim());
	}

	private void establecerValores(List<JTextField> campos, String... valores) {
		for (int indice = 0; indice < campos.size(); indice++) {
			campos.get(indice).setText(valores[indice]);
		}
	}

	private void limpiarFormularioModificar(String entidad) {
		idsModificar.get(entidad).setText("");
		for (JTextField campo : camposModificar.get(entidad)) {
			campo.setText("");
		}
	}

	private void modificarRegistro(String entidad) {
		String idTexto = idsModificar.get(entidad).getText().trim();
		if (idTexto.isEmpty()) {
			mostrarErrorModificar("Selecciona un registro de la lista antes de guardar los cambios.");
			return;
		}

		List<JTextField> campos = camposModificar.get(entidad);
		for (JTextField campo : campos) {
			if (campo.getText().trim().isEmpty()) {
				mostrarErrorModificar("Completa todos los campos antes de guardar los cambios.");
				campo.requestFocusInWindow();
				return;
			}
		}

		try {
			int id = Integer.parseInt(idTexto);
			boolean actualizado;
			switch (entidad) {
			case "Dueño":
				for (dueno registro : duenoDAO.listar()) {
					if (registro.getId_dueño() != id
							&& registro.getC_I().equals(campos.get(3).getText().trim())) {
						mostrarErrorModificar("Ya existe otro dueño con la misma C.I. en la base de datos.");
						return;
					}
				}
				actualizado = duenoDAO.actualizar(new dueno(id, campos.get(0).getText().trim(),
						campos.get(3).getText().trim(), campos.get(2).getText().trim(),
						campos.get(1).getText().trim()));
				break;
			case "Mascota":
				actualizado = mascotaDAO.actualizar(new mascota(campos.get(0).getText().trim(),
						Integer.parseInt(campos.get(1).getText().trim()), campos.get(2).getText().trim(),
						campos.get(3).getText().trim(), campos.get(4).getText().trim(),
						Double.parseDouble(campos.get(5).getText().trim()), id));
				break;
			case "Vacuna":
				actualizado = vacunaDAO.actualizar(new vacuna(id, campos.get(0).getText().trim(),
						campos.get(1).getText().trim(), campos.get(2).getText().trim(),
						campos.get(3).getText().trim(), Integer.parseInt(campos.get(4).getText().trim()),
						Integer.parseInt(campos.get(5).getText().trim())));
				break;
			case "Consulta":
				actualizado = consultaDAO.actualizar(new consulta(id, campos.get(0).getText().trim(),
						campos.get(1).getText().trim(), campos.get(2).getText().trim(),
						Integer.parseInt(campos.get(3).getText().trim()),
						Integer.parseInt(campos.get(4).getText().trim())));
				break;
			case "Veterinario":
				actualizado = veterinarioDAO.actualizar(new veterinario(id,
						campos.get(0).getText().trim(), campos.get(1).getText().trim(),
						campos.get(2).getText().trim()));
				break;
			default:
				throw new IllegalArgumentException("Entidad de modificación desconocida: " + entidad);
			}

			if (!actualizado) {
				mostrarErrorModificar("No se pudieron guardar los cambios. Verifica los datos e inténtalo otra vez.");
				return;
			}
			actualizarTabla(entidad);
			JOptionPane.showMessageDialog(this, "Registro modificado correctamente.", "Éxito",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (NumberFormatException exception) {
			mostrarErrorModificar("La edad y los IDs deben ser números enteros, y el peso debe ser un número válido.");
		}
	}

	private void mostrarErrorModificar(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Error al modificar", JOptionPane.ERROR_MESSAGE);
	}

	private void fijarDimensionPanel(JPanel panel) {
		panel.setMinimumSize(DIMENSION_PANEL_REGISTRO);
		panel.setPreferredSize(DIMENSION_PANEL_REGISTRO);
		panel.setMaximumSize(DIMENSION_PANEL_REGISTRO);
	}

	private void itemMenu(String opcion) {
		switch (opcion) {
			case "Dueño":
				veterinario.setVisible(false);
				vacuna.setVisible(false);
				consulta.setVisible(false);
				mascota.setVisible(false);
				dueño.setVisible(true);
				break;
			case "Mascota":
				vacuna.setVisible(false);
				consulta.setVisible(false);
				veterinario.setVisible(false);
				dueño.setVisible(false);
				mascota.setVisible(true);
				break;
			case "Vacuna":
				veterinario.setVisible(false);
				consulta.setVisible(false);
				dueño.setVisible(false);
				mascota.setVisible(false);
				vacuna.setVisible(true);
				break;
			case "Consulta":
				veterinario.setVisible(false);
				dueño.setVisible(false);
				mascota.setVisible(false);
				vacuna.setVisible(false);
				consulta.setVisible(true);
				break;
			case "Veterinario":
				dueño.setVisible(false);
				mascota.setVisible(false);
				vacuna.setVisible(false);
				consulta.setVisible(false);
				veterinario.setVisible(true);
				break;
		}
	}

	public static void vincularBuscador(JTextField txtBuscador, JList<String> datosOriginales, DefaultListModel<String> modeloLista) {
        
		// Creamos una lista interna en memoria para respaldar los datos de la BD
        ArrayList<String> datosRespaldo = new ArrayList<>();
        
        // Copiamos los datos que ya tiene el JList a nuestro respaldo antes de que el usuario escriba
        ListModel<String> modeloInicial = datosOriginales.getModel();
        for (int i = 0; i < modeloInicial.getSize(); i++) {
            datosRespaldo.add(modeloInicial.getElementAt(i));
        }

        txtBuscador.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { ejecutarFiltro(); }

            @Override
            public void removeUpdate(DocumentEvent e) { ejecutarFiltro(); }

            @Override
            public void changedUpdate(DocumentEvent e) { ejecutarFiltro(); }

            // Lógica de filtrado reutilizable
			private void ejecutarFiltro() {
                String textoBusqueda = txtBuscador.getText().toLowerCase().trim();
                
                modeloLista.clear(); // Limpia el JList visual

                for (String linea : datosRespaldo) {
                    // Al usar .contains(), buscará automáticamente por ID, Nombre, C.I o Teléfono
                    if (linea.toLowerCase().contains(textoBusqueda)) {
                        modeloLista.addElement(linea);
                    }
                }
            }
        });
	}

	private JTabbedPane crearPestañas() {
		pestañas = new JTabbedPane(JTabbedPane.BOTTOM);
		pestañas.setFont(new Font("Segoe UI", Font.BOLD, 14));
		pestañas.setForeground(TEXTO);
		pestañas.setBackground(PANEL);
		pestañas.setBorder(BorderFactory.createEmptyBorder(8, 14, 12, 14));
		pestañas.setUI(new BasicTabbedPaneUI() {
			@Override
			protected void paintTabBackground(Graphics graphics, int tabPlacement, int tabIndex,
					int x, int y, int width, int height, boolean isSelected) {
				graphics.setColor(isSelected
						? (temaOscuro ? RESALTADO : RESALTADO_CLARO)
						: (temaOscuro ? PANEL : PANEL_CLARO));
				graphics.fillRect(x, y, width, height);
			}
		});

		panelRegistrar = new JPanel(new MigLayout("fill, insets 18", "[grow]", "[] [grow]"));
		panelRegistrar.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));
		barraDeMenu = crearBarraDeMenu();
		panelRegistrar.add(crearBarraSuperior(barraDeMenu), "growx, wrap");

		pestañas.addTab("  Añadir / Registrar  ", panelRegistrar);
		panelModificar = new JPanel(new MigLayout("fill, insets 18", "[grow]", "[] [grow]"));
		panelModificar.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));
		barraDeMenuModificar = crearBarraDeMenuModificar();
		panelModificar.add(crearBarraSuperior(barraDeMenuModificar), "growx, wrap");
		pestañas.addTab("  Modificar  ", panelModificar);
		actualizarColorDePestañas(pestañas);
		pestañas.addChangeListener(evento -> actualizarColorDePestañas(pestañas));
		return pestañas;
	}

	private void actualizarColorDePestañas(JTabbedPane pestañas) {
		for (int indice = 0; indice < pestañas.getTabCount(); indice++) {
			Color color = indice == pestañas.getSelectedIndex()
					? (temaOscuro ? ACENTO_BLANCO : ACENTO_NEGRO)
					: (temaOscuro ? TEXTO : TEXTO_CLARO);
			pestañas.setForegroundAt(indice, color);
		}
	}

	private JButton crearBotonTema() {
		JButton boton = new BotonCircular();
		boton.setToolTipText("Cambiar apariencia");
		boton.getAccessibleContext().setAccessibleName("Cambiar apariencia");
		boton.addActionListener(evento -> {
			temaOscuro = !temaOscuro;
			actualizarTema();
		});
		return boton;
	}

	private JPanel crearBarraSuperior(java.awt.Component contenido) {
		JPanel barraSuperior = new JPanel(new MigLayout("fill, insets 0, gap 10", "[grow][]", "[]"));
		barraSuperior.setOpaque(false);
		if (contenido != null) {
			barraSuperior.add(contenido, "growx");
		}
		barraSuperior.add(crearBotonTema(), "w 28!, h 28!, right");
		return barraSuperior;
	}

	private JMenuBar crearBarraDeMenu() {
		JMenuBar barra = new JMenuBar();
		barra.setOpaque(true);
		barra.setBackground(new Color(45, 48, 54));
		barra.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(BORDE),
				BorderFactory.createEmptyBorder(3, 6, 3, 6)));

		String[] opciones = { "Dueño", "Mascota", "Vacuna", "Consulta", "Veterinario" };
		for (String opcion : opciones) {
			JMenuItem menu = new JMenuItem(opcion);
			menu.setOpaque(true);
			menu.setForeground(TEXTO);
			menu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			menu.setMargin(new Insets(4, 10, 4, 5));
			menu.setBackground(new Color(45, 48, 54));
			menu.addActionListener(e -> itemMenu(opcion));
			barra.add(menu);
		}
		return barra;
	}

	private JMenuBar crearBarraDeMenuModificar() {
		JMenuBar barra = new JMenuBar();
		barra.setOpaque(true);
		barra.setBackground(new Color(45, 48, 54));
		barra.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(BORDE),
				BorderFactory.createEmptyBorder(3, 6, 3, 6)));

		String[] opciones = { "Dueño", "Mascota", "Vacuna", "Consulta", "Veterinario" };
		for (String opcion : opciones) {
			JMenuItem menu = new JMenuItem(opcion);
			menu.setOpaque(true);
			menu.setForeground(TEXTO);
			menu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			menu.setMargin(new Insets(4, 10, 4, 5));
			menu.setBackground(new Color(45, 48, 54));
			menu.addActionListener(evento -> tarjetasModificar.show(panelesModificar, opcion));
			barra.add(menu);
		}
		return barra;
	}

	private void actualizarTema() {
		Color fondo = temaOscuro ? FONDO : FONDO_CLARO;
		Color panel = temaOscuro ? PANEL : PANEL_CLARO;
		Color borde = temaOscuro ? BORDE : BORDE_CLARO;
		Color texto = temaOscuro ? TEXTO : TEXTO_CLARO;
		Color resaltado = temaOscuro ? RESALTADO : RESALTADO_CLARO;
		Color barra = temaOscuro ? new Color(45, 48, 54) : new Color(248, 249, 250);

		UIManager.put("TabbedPane.selected", resaltado);
		UIManager.put("TabbedPane.selectHighlight", resaltado);
		UIManager.put("TabbedPane.focus", resaltado);
		UIManager.put("Menu.selectionBackground", resaltado);
		UIManager.put("Menu.selectionForeground", texto);
		UIManager.put("MenuItem.selectionBackground", resaltado);
		UIManager.put("MenuItem.selectionForeground", texto);
		barraDeMenu.updateUI();
		for (java.awt.Component componente : barraDeMenu.getComponents()) {
			if (componente instanceof JMenuItem menuItem) {
				menuItem.updateUI();
			}
		}
		barraDeMenuModificar.updateUI();
		for (java.awt.Component componente : barraDeMenuModificar.getComponents()) {
			if (componente instanceof JMenuItem menuItem) {
				menuItem.updateUI();
			}
		}

		getContentPane().setBackground(fondo);
		pestañas.setBackground(panel);
		pestañas.setForeground(texto);
		panelRegistrar.setBackground(panel);
		panelModificar.setBackground(panel);
		barraDeMenu.setBackground(barra);
		barraDeMenu.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(borde),
				BorderFactory.createEmptyBorder(3, 6, 3, 6)));
		barraDeMenuModificar.setBackground(barra);
		barraDeMenuModificar.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(borde),
				BorderFactory.createEmptyBorder(3, 6, 3, 6)));
		actualizarComponentes(barraDeMenu, barra, texto, resaltado);
		actualizarComponentes(barraDeMenuModificar, barra, texto, resaltado);
		for (int indice = 0; indice < pestañas.getTabCount(); indice++) {
			java.awt.Component contenido = pestañas.getComponentAt(indice);
			contenido.setBackground(panel);
			if (contenido instanceof java.awt.Container contenedor) {
				actualizarComponentes(contenedor, panel, texto, resaltado);
			}
		}
		actualizarComponentes(pestañas, panel, texto, resaltado);
		actualizarColorDePestañas(pestañas);
		pestañas.repaint();
	}

	private void actualizarComponentes(java.awt.Container contenedor, Color fondo, Color texto,
			Color resaltado) {
		for (java.awt.Component componente : contenedor.getComponents()) {
			if (componente instanceof JScrollPane scrollPane) {
				aplicarEstiloScrollPane(scrollPane, temaOscuro);
			}
			if (componente instanceof JButton boton) {
				boton.setBackground(resaltado);
				boton.setForeground(texto);
				boton.repaint();
			}
			if (componente instanceof JMenuItem menuItem) {
				menuItem.setBackground(fondo);
				menuItem.setForeground(texto);
			}
			if (componente instanceof JLabel etiqueta) {
				etiqueta.setForeground(texto);
			}
			if (componente instanceof JTextField campo) {
				campo.setBackground(fondo);
				campo.setForeground(texto);
			}
			if (componente instanceof JPanel panel) {
				panel.setBackground(fondo);
			}
			if (componente instanceof java.awt.Container hijo) {
				actualizarComponentes(hijo, fondo, texto, resaltado);
			}
		}
	}

	private void aplicarEstiloScrollPane(JScrollPane scrollPane, boolean oscuro) {
		Color fondo = oscuro ? new Color(31, 34, 39) : new Color(248, 249, 250);
		Color borde = oscuro ? new Color(75, 80, 89) : new Color(204, 208, 214);
		Color pulgar = oscuro ? new Color(102, 108, 119) : new Color(166, 172, 181);
		Color pulgarActivo = oscuro ? new Color(132, 139, 151) : new Color(125, 132, 143);
		Color seleccion = oscuro ? new Color(67, 73, 82) : new Color(218, 222, 228);
		Color texto = oscuro ? TEXTO : TEXTO_CLARO;

		scrollPane.setBorder(BorderFactory.createLineBorder(borde));
		scrollPane.setBackground(fondo);
		scrollPane.getViewport().setBackground(fondo);
		scrollPane.setOpaque(true);

		if (scrollPane.getViewport().getView() instanceof JList<?> lista) {
			lista.setOpaque(true);
			lista.setBackground(fondo);
			lista.setForeground(texto);
			lista.setSelectionBackground(seleccion);
			lista.setSelectionForeground(texto);
			lista.setBorder(BorderFactory.createEmptyBorder(4, 6, 4, 6));
		}

		scrollPane.getVerticalScrollBar().setUI(crearEstiloBarra(fondo, pulgar, pulgarActivo));
		scrollPane.getHorizontalScrollBar().setUI(crearEstiloBarra(fondo, pulgar, pulgarActivo));
		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10, 10));
		scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(10, 10));
	}

	private BasicScrollBarUI crearEstiloBarra(Color fondo, Color pulgar, Color pulgarActivo) {
		return new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				thumbColor = pulgar;
				trackColor = fondo;
			}

			@Override
			protected JButton createDecreaseButton(int orientation) {
				return crearBotonBarra();
			}

			@Override
			protected JButton createIncreaseButton(int orientation) {
				return crearBotonBarra();
			}

			@Override
			protected void paintThumb(Graphics graphics, JComponent componente, Rectangle limites) {
				if (limites.isEmpty() || !scrollbar.isEnabled()) {
					return;
				}
				Graphics2D dibujo = (Graphics2D) graphics.create();
				dibujo.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);
				dibujo.setColor(isThumbRollover() ? pulgarActivo : pulgar);
				dibujo.fillRoundRect(limites.x + 2, limites.y + 2,
						limites.width - 4, limites.height - 4, 8, 8);
				dibujo.dispose();
			}
		};
	}

	private JButton crearBotonBarra() {
		JButton boton = new JButton();
		boton.setPreferredSize(new Dimension(0, 0));
		boton.setMinimumSize(new Dimension(0, 0));
		boton.setMaximumSize(new Dimension(0, 0));
		return boton;
	}

	private static class BotonCircular extends JButton {
		BotonCircular() {
			setPreferredSize(new Dimension(28, 28));
			setMinimumSize(new Dimension(28, 28));
			setMaximumSize(new Dimension(28, 28));
			setFocusPainted(false);
			setBorderPainted(false);
			setContentAreaFilled(false);
			setOpaque(false);
		}

		@Override
		protected void paintComponent(Graphics graphics) {
			Graphics2D dibujo = (Graphics2D) graphics.create();
			dibujo.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
			int diametro = Math.min(getWidth(), getHeight()) - 4;
			int posicionX = (getWidth() - diametro) / 2;
			int posicionY = (getHeight() - diametro) / 2;
			dibujo.setColor(getBackground());
			dibujo.fillOval(posicionX, posicionY, diametro, diametro);
			dibujo.setColor(getForeground());
			int punto = Math.max(6, diametro / 3);
			dibujo.fillOval((getWidth() - punto) / 2, (getHeight() - punto) / 2, punto, punto);
			dibujo.dispose();
		}
	}

	private static void aplicarEstiloOscuro() {
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException exception) {
			UIManager.put("TabbedPane.background", PANEL);
		}

		UIManager.put("TabbedPane.background", PANEL);
		UIManager.put("TabbedPane.foreground", TEXTO);
		UIManager.put("TabbedPane.selected", RESALTADO);
		UIManager.put("TabbedPane.selectHighlight", RESALTADO);
		UIManager.put("TabbedPane.focus", RESALTADO);
		UIManager.put("TabbedPane.unselectedBackground", PANEL);
		UIManager.put("TabbedPane.unselectedForeground", TEXTO);
		UIManager.put("MenuBar.background", new Color(45, 48, 54));
		UIManager.put("Menu.background", new Color(45, 48, 54));
		UIManager.put("Menu.foreground", TEXTO);
		UIManager.put("Menu.selectionBackground", RESALTADO);
		UIManager.put("Menu.selectionForeground", ACENTO_BLANCO);
		UIManager.put("MenuItem.selectionBackground", RESALTADO);
		UIManager.put("MenuItem.selectionForeground", ACENTO_BLANCO);
		UIManager.put("tituloReg.foreground", TEXTO);
	}

// ----- Métodos en general -----

	public void actualizarTabla(String nombreLista) {
		//try {
			switch (nombreLista) {
			case "Dueño":
				modeloDueños.clear();
				limpiarListaModificar("Dueño");
				for (dueno d : duenoDAO.listar()) {
					String fila = "ID: " + d.getId_dueño() + " | Nombre: " + d.getNombre() + " | C.I: " + d.getC_I() + " | Tel: " + d.getTelefono();
					modeloDueños.addElement(fila);
					agregarFilaModificar("Dueño", fila);
				}
				break;
			case "Mascota":
				modeloMascotas.clear();
				limpiarListaModificar("Mascota");
				for (mascota m : mascotaDAO.listar()) {
					String fila = "ID: " + m.getId_mascota() + " | Nombre: " + m.getNombre() + " | Especie: " + m.getEspecie() + " | Raza: " + m.getRaza() + " | Edad: " + m.getEdad() + " | Sexo: " + m.getSexo() + " | Peso: " + m.getPeso();
					modeloMascotas.addElement(fila);
					agregarFilaModificar("Mascota", fila);
				}
				break;
			case "Vacuna":
				modeloVacunas.clear();
				limpiarListaModificar("Vacuna");
				for (vacuna v : vacunaDAO.listar()) {
					String fila = "ID: " + v.getId_vacuna() + " | Tipo: " + v.getTipo_vacuna() + " | Fecha: " + v.getFecha_vacuna() + " | Hora: " + v.getHora_vacuna() + " | Vence: " + v.getVencimiento() + " | ID mascota: " + v.getId_mascota() + " | ID consulta: " + v.getId_consulta();
					modeloVacunas.addElement(fila);
					agregarFilaModificar("Vacuna", fila);
				}
				break;
			case "Consulta":
				modeloConsultas.clear();
				limpiarListaModificar("Consulta");
				for (consulta c : consultaDAO.listar()) {
					String fila = "ID: " + c.getId_consulta() + " | Procedimiento: " + c.getProcedimiento() + " | Fecha de consulta: " + c.getFecha_consulta() + " | Hora: " + c.getHora() + " | ID mascota: " + c.getId_mascota() + " | ID veterinario: " + c.getId_veterinario();
					modeloConsultas.addElement(fila);
					agregarFilaModificar("Consulta", fila);
				}
				break;
			case "Veterinario":
				modeloVeterinarios.clear();
				limpiarListaModificar("Veterinario");
				for (veterinario v : veterinarioDAO.listar()) {
					String fila = "ID: " + v.getId_veterinario() + " | Nombre: " + v.getNombre_veterinario() + " | Especialidad: " + v.getEspecialidad();
					modeloVeterinarios.addElement(fila);
					agregarFilaModificar("Veterinario", fila);
				}
				break;
			}
		//} catch (e exception) {
			//JOptionPane.showMessageDialog(this,
					//"No se pudieron cargar los registros:\n" + exception.getMessage(),
					//"Error de base de datos", JOptionPane.ERROR_MESSAGE);
		//}
	}

	public void registrar(String nombreLista) {
		try {
			switch (nombreLista) {
			case "Dueño":
				if (hayCamposVacios(nombreField, eMailField, telefonoField, CIField)) {
					return;
				}
				for (dueno dueño : duenoDAO.listar()) {
					if (CIField.getText().trim().equals(dueño.getC_I())) {
						mostrarError("Ya existe un dueño con la misma C.I. en la base de datos.");
						return;
					}
				}
				duenoDAO.guardar(new dueno(0, nombreField.getText().trim(), CIField.getText().trim(),
						telefonoField.getText().trim(), eMailField.getText().trim()));
				limpiarCampos("Dueño");
				actualizarTabla("Dueño");
				break;

			case "Mascota":
				if (hayCamposVacios(nombreMascotaField, edadMascotaField, especieMascotaField,
						razaMascotaField, sexoMascotaField, pesoMascotaField)) {
					return;
				}
				mascotaDAO.guardar(new mascota(nombreMascotaField.getText().trim(),
						Integer.parseInt(edadMascotaField.getText().trim()), especieMascotaField.getText().trim(),
						razaMascotaField.getText().trim(), sexoMascotaField.getText().trim(),
						Double.parseDouble(pesoMascotaField.getText().trim()), 0));
				limpiarCampos("Mascota");
				actualizarTabla("Mascota");
				break;

			case "Vacuna":
				if (hayCamposVacios(tipoVacunaField, fechaVacunaField, horaVacunaField, vencimientoField,
						idMascotaVacunaField, idConsultaField)) {
					return;
				}
				int idVacuna = vacunaDAO.guardar(new vacuna(0, tipoVacunaField.getText().trim(),
						fechaVacunaField.getText().trim(), horaVacunaField.getText().trim(),
						vencimientoField.getText().trim(), Integer.parseInt(idMascotaVacunaField.getText().trim()),
						Integer.parseInt(idConsultaField.getText().trim())));
				if (idVacuna < 0) {
					mostrarError("No se pudo registrar la vacuna. Verifica que los IDs de mascota y consulta existan.");
					return;
				}
				limpiarCampos("Vacuna");
				actualizarTabla("Vacuna");
				break;

			case "Consulta":
				if (hayCamposVacios(procedimientoField, fechaConsultaField, horaConsultaField, idMascotaField,
						idVeterinarioField)) {
					return;
				}
				int idConsulta = consultaDAO.guardar(new consulta(0, procedimientoField.getText().trim(),
						fechaConsultaField.getText().trim(), horaConsultaField.getText().trim(),
						Integer.parseInt(idMascotaField.getText().trim()),
						Integer.parseInt(idVeterinarioField.getText().trim())));
				if (idConsulta < 0) {
					mostrarError("No se pudo registrar la consulta. Verifica que los IDs de mascota y veterinario existan.");
					return;
				}
				limpiarCampos("Consulta");
				actualizarTabla("Consulta");
				break;

			case "Veterinario":
				if (hayCamposVacios(nombreVeterinarioField, contraseñaVeterinarioField, especialidadField)) {
					return;
				}
				veterinarioDAO.guardar(new veterinario(0, nombreVeterinarioField.getText().trim(),
						contraseñaVeterinarioField.getText().trim(), especialidadField.getText().trim()));
				limpiarCampos("Veterinario");
				actualizarTabla("Veterinario");
				break;
			}
		} catch (NumberFormatException exception) {
			mostrarError("La edad y los IDs deben ser números enteros, y el peso debe ser un número válido.");
		//} catch (SQLException exception) {
			//mostrarError("No se pudo guardar el registro:\n" + exception.getMessage());
		}
	}

	private boolean hayCamposVacios(JTextField... campos) {
		for (JTextField campo : campos) {
			if (campo.getText().trim().isEmpty()) {
				mostrarError("Completa todos los campos antes de registrar.");
				campo.requestFocusInWindow();
				return true;
			}
		}
		return false;
	}

	private void mostrarError(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Error de registro", JOptionPane.ERROR_MESSAGE);
	}

	private void limpiarCampos(String nombreLista) {
		switch (nombreLista) {
		case "Dueño":
			nombreField.setText("");
			eMailField.setText("");
			telefonoField.setText("");
			CIField.setText("");
			break;
		case "Mascota":
			nombreMascotaField.setText("");
			edadMascotaField.setText("");
			especieMascotaField.setText("");
			razaMascotaField.setText("");
			sexoMascotaField.setText("");
			pesoMascotaField.setText("");
			break;
		case "Vacuna":
			tipoVacunaField.setText("");
			fechaVacunaField.setText("");
			horaVacunaField.setText("");
			vencimientoField.setText("");
			idMascotaVacunaField.setText("");
			idConsultaField.setText("");
			break;
		case "Consulta":
			procedimientoField.setText("");
			fechaConsultaField.setText("");
			horaConsultaField.setText("");
			idMascotaField.setText("");
			idVeterinarioField.setText("");
			break;
		case "Veterinario":
			nombreVeterinarioField.setText("");
			contraseñaVeterinarioField.setText("");
			especialidadField.setText("");
			break;
		}
	}

	private void eliminar(String nombreLista) {
		//try {
			switch (nombreLista) {
			case "Dueño":
				String selectedDueño = listaDueños.getSelectedValue();
				String[] parts = selectedDueño.split("\\|");
				String id = parts[0].trim().split(":")[1].trim();
				if (id != null) {
					duenoDAO.eliminar(Integer.parseInt(id));
					JOptionPane.showMessageDialog(this, "Registro eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
				}
				actualizarTabla("Dueño");
				break;
			case "Mascota":
				String selectedMascota = listaMascotas.getSelectedValue();
				String[] parts2 = selectedMascota.split("\\|");
				String id2 = parts2[0].trim().split(":")[1].trim();
				if (id2 != null) {
					mascotaDAO.eliminar(Integer.parseInt(id2));
					JOptionPane.showMessageDialog(this, "Registro eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
				}
				actualizarTabla("Mascota");
				break;
			case "Vacuna":
				String selectedVacuna = listaVacunas.getSelectedValue();
				String[] parts3 = selectedVacuna.split("\\|");
				String id3 = parts3[0].trim().split(":")[1].trim();
				if (id3 != null) {
					vacunaDAO.eliminar(Integer.parseInt(id3));
					JOptionPane.showMessageDialog(this, "Registro eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
				}
				actualizarTabla("Vacuna");
				break;
			case "Consulta":
				String selectedConsulta = listaConsultas.getSelectedValue();
				String[] parts4 = selectedConsulta.split("\\|");
				String id4 = parts4[0].trim().split(":")[1].trim();
				if (id4 != null) {
					consultaDAO.eliminar(Integer.parseInt(id4));
					JOptionPane.showMessageDialog(this, "Registro eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
				}
				actualizarTabla("Consulta");
				break;
			case "Veterinario":
				String selectedVeterinario = listaVeterinarios.getSelectedValue();
				String[] parts5 = selectedVeterinario.split("\\|");
				String id5 = parts5[0].trim().split(":")[1].trim();
				if (id5 != null) {
					veterinarioDAO.eliminar(Integer.parseInt(id5));
					JOptionPane.showMessageDialog(this, "Registro eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
				}
				actualizarTabla("Veterinario");
				break;
			}
		//} catch (SQLException exception) {
			//JOptionPane.showMessageDialog(this,
			//		"No se pudo eliminar el registro:\n" + exception.getMessage(),
			//		"Error de base de datos", JOptionPane.ERROR_MESSAGE);
		//}
	}

	/*private void modificar(String nombreLista, String id, String[] nuevosDatos) {
		try {
			switch (nombreLista) {
			case "Dueño":
				actualizarTabla("Dueño");
				break;
			case "Mascota":
				actualizarTabla("Mascota");
				break;
			case "Vacuna":
				actualizarTabla("Vacuna");
				break;
			case "Consulta":
				actualizarTabla("Consulta");
				break;
			case "Veterinario":
				actualizarTabla("Veterinario");
				break;
			}
		} catch (SQLException exception) {
			JOptionPane.showMessageDialog(this,
					"No se pudo modificar el registro:\n" + exception.getMessage(),
					"Error de base de datos", JOptionPane.ERROR_MESSAGE);
		}
	}*/

// ----------------------------------------------------
	public static void main(String[] args) {
		aplicarEstiloOscuro();
		SwingUtilities.invokeLater(() -> new Interfaz().setVisible(true));
	}
}