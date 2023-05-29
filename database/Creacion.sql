USE [EquipoGuia]
GO
/****** Object:  User [admin]    Script Date: 5/29/2023 12:10:40 PM ******/
CREATE USER [admin] FOR LOGIN [admin] WITH DEFAULT_SCHEMA=[dbo]
GO
ALTER ROLE [db_owner] ADD MEMBER [admin]
GO
/****** Object:  Table [dbo].[Actividades]    Script Date: 5/29/2023 12:10:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Actividades](
	[idActividad] [int] IDENTITY(1,1) NOT NULL,
	[idPlanDeTrabajo] [int] NOT NULL,
	[semana] [int] NOT NULL,
	[idTipoActividad] [int] NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[cantidadDiasAnuncio] [int] NOT NULL,
	[fechaPublicacion] [date] NOT NULL,
	[cantidadRecordatorios] [int] NOT NULL,
	[idModalidad] [int] NOT NULL,
	[enlace] [varchar](100) NULL,
	[afiche] [varbinary](max) NOT NULL,
	[idEstado] [int] NOT NULL,
	[fechaHora] [datetime] NOT NULL,
 CONSTRAINT [PK_Actividades] PRIMARY KEY CLUSTERED 
(
	[idActividad] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Asistentes]    Script Date: 5/29/2023 12:10:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Asistentes](
	[idAsistente] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[idSede] [varchar](2) NOT NULL,
	[correo] [varchar](50) NOT NULL,
	[contraseña] [varchar](8) NOT NULL,
	[celular] [int] NOT NULL,
 CONSTRAINT [PK_Asistentes] PRIMARY KEY CLUSTERED 
(
	[idAsistente] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Comentarios]    Script Date: 5/29/2023 12:10:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Comentarios](
	[idComentario] [int] IDENTITY(1,1) NOT NULL,
	[idActividad] [int] NOT NULL,
	[idProfesor] [int] NOT NULL,
	[fecha] [datetime] NOT NULL,
	[idComentarioOriginal] [int] NULL,
	[comentario] [varchar](100) NOT NULL,
 CONSTRAINT [PK_Comentarios] PRIMARY KEY CLUSTERED 
(
	[idComentario] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Estados]    Script Date: 5/29/2023 12:10:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Estados](
	[idEstado] [int] IDENTITY(1,1) NOT NULL,
	[estado] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Estados] PRIMARY KEY CLUSTERED 
(
	[idEstado] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Estudiantes]    Script Date: 5/29/2023 12:10:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Estudiantes](
	[idEstudiante] [int] IDENTITY(1,1) NOT NULL,
	[idSede] [varchar](2) NOT NULL,
	[carne] [int] NOT NULL,
	[apellido1] [varchar](50) NOT NULL,
	[apellido2] [varchar](50) NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[segundoNombre] [varchar](50) NULL,
	[correo] [varchar](50) NOT NULL,
	[numeroCelular] [int] NOT NULL,
	[contraseña] [varchar](8) NULL,
 CONSTRAINT [PK_Estudiantes] PRIMARY KEY CLUSTERED 
(
	[idEstudiante] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Evidencias]    Script Date: 5/29/2023 12:10:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Evidencias](
	[idEvidencia] [int] IDENTITY(1,1) NOT NULL,
	[evidencia] [varbinary](max) NOT NULL,
	[idActividad] [int] NOT NULL,
 CONSTRAINT [PK_Evidencias] PRIMARY KEY CLUSTERED 
(
	[idEvidencia] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Modalidades]    Script Date: 5/29/2023 12:10:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Modalidades](
	[idModalidad] [int] IDENTITY(1,1) NOT NULL,
	[modalidad] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Modalidades] PRIMARY KEY CLUSTERED 
(
	[idModalidad] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ModificacionesProfesores]    Script Date: 5/29/2023 12:10:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ModificacionesProfesores](
	[idModificacionProfesor] [int] IDENTITY(1,1) NOT NULL,
	[idProfesor] [int] NOT NULL,
	[idAsistente] [int] NOT NULL,
	[idTipoModificacion] [int] NOT NULL,
	[fecha] [date] NOT NULL,
 CONSTRAINT [PK_ModificacionesProfesoresGuias] PRIMARY KEY CLUSTERED 
(
	[idModificacionProfesor] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PlanDeTrabajo]    Script Date: 5/29/2023 12:10:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PlanDeTrabajo](
	[idPlanDeTrabajo] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[idProfesorCoordinador] [int] NOT NULL,
 CONSTRAINT [PK_PlanDeTrabajo] PRIMARY KEY CLUSTERED 
(
	[idPlanDeTrabajo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Profesores]    Script Date: 5/29/2023 12:10:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Profesores](
	[idProfesor] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[idSede] [varchar](2) NOT NULL,
	[correo] [varchar](50) NOT NULL,
	[numeroOficina] [int] NOT NULL,
	[numeroCelular] [int] NOT NULL,
	[foto] [varbinary](max) NULL,
	[contraseña] [varchar](8) NOT NULL,
	[darDeBaja] [bit] NOT NULL,
 CONSTRAINT [PK_Profesores] PRIMARY KEY CLUSTERED 
(
	[idProfesor] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ProfesoresActividades]    Script Date: 5/29/2023 12:10:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProfesoresActividades](
	[idProfesor] [int] NOT NULL,
	[idActividad] [int] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ProfesoresGuias]    Script Date: 5/29/2023 12:10:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProfesoresGuias](
	[idProfesor] [int] NOT NULL,
	[codigo] [varchar](10) NOT NULL,
	[coordinador] [bit] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Recordatorios]    Script Date: 5/29/2023 12:10:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Recordatorios](
	[idRecordatorio] [int] IDENTITY(1,1) NOT NULL,
	[idActividad] [int] NOT NULL,
	[fecha] [date] NOT NULL,
 CONSTRAINT [PK_Recordatorios] PRIMARY KEY CLUSTERED 
(
	[idRecordatorio] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Sedes]    Script Date: 5/29/2023 12:10:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Sedes](
	[idSede] [varchar](2) NOT NULL,
	[nombre] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Sedes] PRIMARY KEY CLUSTERED 
(
	[idSede] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TiposActividad]    Script Date: 5/29/2023 12:10:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TiposActividad](
	[idTipoActividad] [int] IDENTITY(1,1) NOT NULL,
	[tipo] [varchar](50) NOT NULL,
 CONSTRAINT [PK_TiposActividad] PRIMARY KEY CLUSTERED 
(
	[idTipoActividad] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TiposModificaciones]    Script Date: 5/29/2023 12:10:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TiposModificaciones](
	[idTipoModificacion] [int] IDENTITY(1,1) NOT NULL,
	[descripcion] [varchar](50) NOT NULL,
 CONSTRAINT [PK_TiposModificaciones] PRIMARY KEY CLUSTERED 
(
	[idTipoModificacion] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Actividades]  WITH CHECK ADD  CONSTRAINT [FK_Actividades_Estados] FOREIGN KEY([idEstado])
REFERENCES [dbo].[Estados] ([idEstado])
GO
ALTER TABLE [dbo].[Actividades] CHECK CONSTRAINT [FK_Actividades_Estados]
GO
ALTER TABLE [dbo].[Actividades]  WITH CHECK ADD  CONSTRAINT [FK_Actividades_Modalidades] FOREIGN KEY([idModalidad])
REFERENCES [dbo].[Modalidades] ([idModalidad])
GO
ALTER TABLE [dbo].[Actividades] CHECK CONSTRAINT [FK_Actividades_Modalidades]
GO
ALTER TABLE [dbo].[Actividades]  WITH CHECK ADD  CONSTRAINT [FK_Actividades_PlanDeTrabajo] FOREIGN KEY([idPlanDeTrabajo])
REFERENCES [dbo].[PlanDeTrabajo] ([idPlanDeTrabajo])
GO
ALTER TABLE [dbo].[Actividades] CHECK CONSTRAINT [FK_Actividades_PlanDeTrabajo]
GO
ALTER TABLE [dbo].[Actividades]  WITH CHECK ADD  CONSTRAINT [FK_Actividades_TiposActividad] FOREIGN KEY([idTipoActividad])
REFERENCES [dbo].[TiposActividad] ([idTipoActividad])
GO
ALTER TABLE [dbo].[Actividades] CHECK CONSTRAINT [FK_Actividades_TiposActividad]
GO
ALTER TABLE [dbo].[Asistentes]  WITH CHECK ADD  CONSTRAINT [FK_Asistentes_Sedes] FOREIGN KEY([idSede])
REFERENCES [dbo].[Sedes] ([idSede])
GO
ALTER TABLE [dbo].[Asistentes] CHECK CONSTRAINT [FK_Asistentes_Sedes]
GO
ALTER TABLE [dbo].[Comentarios]  WITH CHECK ADD  CONSTRAINT [FK_Comentarios_Actividades] FOREIGN KEY([idActividad])
REFERENCES [dbo].[Actividades] ([idActividad])
GO
ALTER TABLE [dbo].[Comentarios] CHECK CONSTRAINT [FK_Comentarios_Actividades]
GO
ALTER TABLE [dbo].[Comentarios]  WITH CHECK ADD  CONSTRAINT [FK_Comentarios_Comentarios] FOREIGN KEY([idComentarioOriginal])
REFERENCES [dbo].[Comentarios] ([idComentario])
GO
ALTER TABLE [dbo].[Comentarios] CHECK CONSTRAINT [FK_Comentarios_Comentarios]
GO
ALTER TABLE [dbo].[Comentarios]  WITH CHECK ADD  CONSTRAINT [FK_Comentarios_Profesores] FOREIGN KEY([idProfesor])
REFERENCES [dbo].[Profesores] ([idProfesor])
GO
ALTER TABLE [dbo].[Comentarios] CHECK CONSTRAINT [FK_Comentarios_Profesores]
GO
ALTER TABLE [dbo].[Estudiantes]  WITH CHECK ADD  CONSTRAINT [FK_Estudiantes_Sedes] FOREIGN KEY([idSede])
REFERENCES [dbo].[Sedes] ([idSede])
GO
ALTER TABLE [dbo].[Estudiantes] CHECK CONSTRAINT [FK_Estudiantes_Sedes]
GO
ALTER TABLE [dbo].[Evidencias]  WITH CHECK ADD  CONSTRAINT [FK_Evidencias_Actividades] FOREIGN KEY([idActividad])
REFERENCES [dbo].[Actividades] ([idActividad])
GO
ALTER TABLE [dbo].[Evidencias] CHECK CONSTRAINT [FK_Evidencias_Actividades]
GO
ALTER TABLE [dbo].[ModificacionesProfesores]  WITH CHECK ADD  CONSTRAINT [FK_ModificacionesProfesoresGuias_Asistentes] FOREIGN KEY([idAsistente])
REFERENCES [dbo].[Asistentes] ([idAsistente])
GO
ALTER TABLE [dbo].[ModificacionesProfesores] CHECK CONSTRAINT [FK_ModificacionesProfesoresGuias_Asistentes]
GO
ALTER TABLE [dbo].[ModificacionesProfesores]  WITH CHECK ADD  CONSTRAINT [FK_ModificacionesProfesoresGuias_Profesores] FOREIGN KEY([idProfesor])
REFERENCES [dbo].[Profesores] ([idProfesor])
GO
ALTER TABLE [dbo].[ModificacionesProfesores] CHECK CONSTRAINT [FK_ModificacionesProfesoresGuias_Profesores]
GO
ALTER TABLE [dbo].[ModificacionesProfesores]  WITH CHECK ADD  CONSTRAINT [FK_ModificacionesProfesoresGuias_TiposModificaciones] FOREIGN KEY([idTipoModificacion])
REFERENCES [dbo].[TiposModificaciones] ([idTipoModificacion])
GO
ALTER TABLE [dbo].[ModificacionesProfesores] CHECK CONSTRAINT [FK_ModificacionesProfesoresGuias_TiposModificaciones]
GO
ALTER TABLE [dbo].[PlanDeTrabajo]  WITH CHECK ADD  CONSTRAINT [FK_PlanDeTrabajo_Profesores] FOREIGN KEY([idProfesorCoordinador])
REFERENCES [dbo].[Profesores] ([idProfesor])
GO
ALTER TABLE [dbo].[PlanDeTrabajo] CHECK CONSTRAINT [FK_PlanDeTrabajo_Profesores]
GO
ALTER TABLE [dbo].[Profesores]  WITH CHECK ADD  CONSTRAINT [FK_Profesores_Sedes] FOREIGN KEY([idSede])
REFERENCES [dbo].[Sedes] ([idSede])
GO
ALTER TABLE [dbo].[Profesores] CHECK CONSTRAINT [FK_Profesores_Sedes]
GO
ALTER TABLE [dbo].[ProfesoresActividades]  WITH CHECK ADD  CONSTRAINT [FK_ProfesoresActividades_Actividades] FOREIGN KEY([idActividad])
REFERENCES [dbo].[Actividades] ([idActividad])
GO
ALTER TABLE [dbo].[ProfesoresActividades] CHECK CONSTRAINT [FK_ProfesoresActividades_Actividades]
GO
ALTER TABLE [dbo].[ProfesoresActividades]  WITH CHECK ADD  CONSTRAINT [FK_ProfesoresActividades_Profesores] FOREIGN KEY([idProfesor])
REFERENCES [dbo].[Profesores] ([idProfesor])
GO
ALTER TABLE [dbo].[ProfesoresActividades] CHECK CONSTRAINT [FK_ProfesoresActividades_Profesores]
GO
ALTER TABLE [dbo].[ProfesoresGuias]  WITH CHECK ADD  CONSTRAINT [FK_ProfesoresGuias_Profesores] FOREIGN KEY([idProfesor])
REFERENCES [dbo].[Profesores] ([idProfesor])
GO
ALTER TABLE [dbo].[ProfesoresGuias] CHECK CONSTRAINT [FK_ProfesoresGuias_Profesores]
GO
ALTER TABLE [dbo].[Recordatorios]  WITH CHECK ADD  CONSTRAINT [FK_Recordatorios_Actividades] FOREIGN KEY([idActividad])
REFERENCES [dbo].[Actividades] ([idActividad])
GO
ALTER TABLE [dbo].[Recordatorios] CHECK CONSTRAINT [FK_Recordatorios_Actividades]
GO
