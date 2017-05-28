USE [TritonLink]
GO
/****** Object:  Table [dbo].[ATTENDANCE]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ATTENDANCE](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[student_id] [int] NOT NULL,
	[quarter_id] [int] NOT NULL,
 CONSTRAINT [PK_ATTENDANCE] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[BOOK]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BOOK](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[title] [text] NOT NULL,
	[author] [text] NOT NULL,
 CONSTRAINT [PK_BOOK] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[BOOK_AVAILABILITY]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BOOK_AVAILABILITY](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[book_id] [int] NOT NULL,
	[condition_id] [int] NOT NULL,
	[status] [text] NOT NULL,
 CONSTRAINT [PK_BOOK_AVAILABILITY] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[BOOK_CONDITION]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BOOK_CONDITION](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[type] [text] NOT NULL,
	[isRental] [int] NOT NULL,
	[price] [int] NOT NULL,
 CONSTRAINT [PK_BOOK_CONDITION] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[BSMS_STUDENT]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BSMS_STUDENT](
	[id] [int] NOT NULL,
	[major_id] [int] NOT NULL,
	[minor_id] [int] NOT NULL,
	[college_id] [int] NOT NULL,
 CONSTRAINT [PK_BSMS_STUDENT] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[CLASS]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CLASS](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[course_id] [int] NOT NULL,
	[title] [varchar](50) NOT NULL,
	[quarter_id] [int] NOT NULL,
 CONSTRAINT [PK_CLASS] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[COLLEGE]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[COLLEGE](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [text] NOT NULL,
 CONSTRAINT [PK_COLLEGE] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[COMMITTEE]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[COMMITTEE](
	[id] [int] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_COMMITTEE] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[COMMITTEE_FACULTY]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[COMMITTEE_FACULTY](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[committee_id] [int] NOT NULL,
	[faculty_id] [int] NOT NULL,
 CONSTRAINT [PK_COMMITTEE_FACULTY] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[COMMITTEE_GRAD]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[COMMITTEE_GRAD](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[committee_id] [int] NOT NULL,
	[grad_id] [int] NOT NULL,
 CONSTRAINT [PK_COMMITTEE_GRAD] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[COMMITTEE_PHD]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[COMMITTEE_PHD](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[committee_id] [int] NOT NULL,
	[phd_candidate_id] [int] NOT NULL,
 CONSTRAINT [PK_COMMITTEE_PHD] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[COURSE]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[COURSE](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[subject_id] [int] NOT NULL,
	[prevNum] [text] NULL,
	[currNum] [text] NOT NULL,
	[unitFrom] [int] NOT NULL,
	[unitTo] [int] NOT NULL,
	[letter_option] [bit] NOT NULL,
	[SU_option] [bit] NOT NULL,
	[labwork] [bit] NOT NULL,
	[instructor_consent] [bit] NOT NULL,
 CONSTRAINT [PK_COURSE] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[COURSE_CATEGORY_REQUIREMENT]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[COURSE_CATEGORY_REQUIREMENT](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[type_id] [int] NOT NULL,
	[min_gpa] [float] NOT NULL,
	[min_unit] [int] NOT NULL,
 CONSTRAINT [PK_COURSE_CATEGORY_REQUIREMENT] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[COURSE_SUBJECT]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[COURSE_SUBJECT](
	[subject_id] [int] IDENTITY(1,1) NOT NULL,
	[symbol] [text] NOT NULL,
	[dept_id] [int] NOT NULL,
 CONSTRAINT [PK_COURSE_SUBJECT] PRIMARY KEY CLUSTERED 
(
	[subject_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[COURSE_TYPE]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[COURSE_TYPE](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) NOT NULL,
 CONSTRAINT [PK_COURSE_TYPE] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[COURSE_TYPE_CATEGORY]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[COURSE_TYPE_CATEGORY](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[course_id] [int] NOT NULL,
	[type_id] [int] NOT NULL,
 CONSTRAINT [PK_COURSE_TYPE_CATEGORY] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[DEGREE]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DEGREE](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) NOT NULL,
	[type_id] [int] NOT NULL,
	[institution] [varchar](50) NOT NULL,
 CONSTRAINT [PK_DEGREE] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[DEGREE_TYPE]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DEGREE_TYPE](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) NOT NULL,
 CONSTRAINT [PK_DEGREE_TYPE] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[DEPARTMENT]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DEPARTMENT](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [text] NOT NULL,
 CONSTRAINT [PK_DEPARTMENT] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[DISCUSSION]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DISCUSSION](
	[id] [int] NOT NULL,
	[required] [int] NOT NULL,
 CONSTRAINT [PK_DISCUSSION] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[ENROLLMENT]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ENROLLMENT](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[student_id] [int] NOT NULL,
	[section_id] [int] NOT NULL,
	[letter_option] [bit] NOT NULL,
	[su_option] [bit] NOT NULL,
	[grade] [varchar](50) NULL,
	[unit] [int] NOT NULL,
 CONSTRAINT [PK_ENROLLMENT] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[FACULTY]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[FACULTY](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [text] NOT NULL,
	[title] [text] NOT NULL,
	[dept_id] [int] NOT NULL,
 CONSTRAINT [PK_FACULTY] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[GRAD]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GRAD](
	[id] [int] NOT NULL,
	[dept_id] [int] NOT NULL,
 CONSTRAINT [PK_GRAD] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[GRADE_CONVERSION]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GRADE_CONVERSION](
	[letter_grade] [varchar](2) NOT NULL,
	[number_grade] [decimal](2, 1) NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[MASTER]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MASTER](
	[id] [int] NOT NULL,
 CONSTRAINT [PK_MASTER] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[MEETING]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MEETING](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[start_time] [time](7) NOT NULL,
	[end_time] [time](7) NOT NULL,
	[room] [int] NOT NULL,
	[building] [text] NOT NULL,
	[section_id] [int] NOT NULL,
 CONSTRAINT [PK_MEETING] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[MS_CONCENTRATION]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MS_CONCENTRATION](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[degree_id] [int] NOT NULL,
	[course_id] [int] NOT NULL,
 CONSTRAINT [PK_MS_CONCENTRATION] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[NON_DISCUSSION]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NON_DISCUSSION](
	[id] [int] NOT NULL,
	[type] [text] NOT NULL,
 CONSTRAINT [PK_NON_DISCUSSION] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[PHD]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PHD](
	[id] [int] NOT NULL,
 CONSTRAINT [PK_PHD] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[PHD_CANDIDATE]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PHD_CANDIDATE](
	[id] [int] NOT NULL,
	[advisor_id] [int] NOT NULL,
 CONSTRAINT [PK_PHD_CANDIDATE] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[PHD_PRECANDIDATE]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PHD_PRECANDIDATE](
	[id] [int] NOT NULL,
 CONSTRAINT [PK_PHD_PRECANDIDATE] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[PREREQUISITE]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PREREQUISITE](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[course_id] [int] NOT NULL,
	[prereq_id] [int] NOT NULL,
 CONSTRAINT [PK_PREREQUISITE] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[PROBATION_PERIOD]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PROBATION_PERIOD](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[student_id] [int] NOT NULL,
	[quarter_id] [int] NOT NULL,
	[reason] [text] NOT NULL,
 CONSTRAINT [PK_PROBATION_PERIOD] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[QUARTER]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[QUARTER](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name_id] [int] NOT NULL,
	[year] [int] NOT NULL,
 CONSTRAINT [PK_QUARTER] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[QUARTER_NAME]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[QUARTER_NAME](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_QUARTER_NAME] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[RESIDENT_STATUS]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RESIDENT_STATUS](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[type] [text] NOT NULL,
 CONSTRAINT [PK_RESIDENT_STATUS] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[REVIEW_SESSION]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[REVIEW_SESSION](
	[id] [int] NOT NULL,
	[month] [text] NOT NULL,
	[day] [int] NOT NULL,
	[weekday] [text] NOT NULL,
 CONSTRAINT [PK_REVIEW_SESSION] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[SECTION]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SECTION](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[class_id] [int] NOT NULL,
	[faculty_id] [int] NOT NULL,
	[enroll_limit] [int] NOT NULL,
 CONSTRAINT [PK_SECTION] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[SECTION_BOOKLIST]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SECTION_BOOKLIST](
	[id] [int] NOT NULL,
	[section_id] [int] NOT NULL,
	[book_id] [int] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_SECTION_BOOKLIST] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[STUDENT]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[STUDENT](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[pid] [int] NOT NULL,
	[firstname] [text] NOT NULL,
	[middlename] [text] NULL,
	[lastname] [text] NOT NULL,
	[ssn] [int] NOT NULL,
	[resident_status] [int] NOT NULL,
 CONSTRAINT [PK_STUDENT] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[UNDERGRAD]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UNDERGRAD](
	[id] [int] NOT NULL,
	[major_id] [int] NOT NULL,
	[minor_id] [int] NOT NULL,
	[college_id] [int] NOT NULL,
 CONSTRAINT [PK_UNDERGRAD] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[UNIT_CATEGORY]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UNIT_CATEGORY](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[totalunit] [int] NOT NULL,
 CONSTRAINT [PK_UNIT_CATEGORY] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[UNIT_COURSE_CATEGORY]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UNIT_COURSE_CATEGORY](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[unit_category_id] [int] NOT NULL,
	[course_category_id] [int] NOT NULL,
 CONSTRAINT [PK_UNIT_COURSE_CATEGORY] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[UNIT_REQUIREMENT]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UNIT_REQUIREMENT](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[department_id] [int] NOT NULL,
	[degree_id] [int] NOT NULL,
	[totalunit_id] [int] NOT NULL,
 CONSTRAINT [PK_UNIT_REQUIREMENT] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[WAITLIST]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[WAITLIST](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[section_id] [int] NOT NULL,
	[student_id] [int] NOT NULL,
 CONSTRAINT [PK_WAITLIST] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[WEEKLY_MEETING]    Script Date: 5/20/2017 11:35:48 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[WEEKLY_MEETING](
	[id] [int] NOT NULL,
	[weekday] [text] NOT NULL,
 CONSTRAINT [PK_WEEKLY_MEETING] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET IDENTITY_INSERT [dbo].[COLLEGE] ON 

INSERT [dbo].[COLLEGE] ([id], [name]) VALUES (1, N'Thurgood Marshal')
INSERT [dbo].[COLLEGE] ([id], [name]) VALUES (2, N'John Muir')
INSERT [dbo].[COLLEGE] ([id], [name]) VALUES (3, N'Revelle')
INSERT [dbo].[COLLEGE] ([id], [name]) VALUES (4, N'Earl Warren')
INSERT [dbo].[COLLEGE] ([id], [name]) VALUES (5, N'Eleanor Roosevelt')
INSERT [dbo].[COLLEGE] ([id], [name]) VALUES (6, N'Sixth')
SET IDENTITY_INSERT [dbo].[COLLEGE] OFF
SET IDENTITY_INSERT [dbo].[COURSE_SUBJECT] ON 

INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (1, N'AIP', 1)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (2, N'AAF', 64)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (3, N'ANES', 2)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (4, N'ANBI', 3)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (5, N'ANAR', 3)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (6, N'ANTH', 3)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (7, N'ANCS', 3)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (8, N'AESE', 10)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (9, N'BIEB', 5)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (10, N'BIPN', 5)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (11, N'BIBC', 5)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (12, N'BILD', 5)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (13, N'BIMM', 5)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (14, N'CENG', 37)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (15, N'CHEM', 6)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (16, N'CLRE', 7)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (17, N'COGS', 8)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (18, N'COMM', 9)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (19, N'CSE', 10)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (20, N'CONT', 11)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (21, N'CGS', 12)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (22, N'CAT', 13)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (23, N'TDMV', 55)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (24, N'DOC', 14)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (25, N'ECON', 15)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (26, N'EAP', 57)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (27, N'EDS', 16)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (28, N'ECE', 17)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (29, N'EMED', 18)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (30, N'ENG', 50)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (31, N'ELWR', 4)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (32, N'ENVR', 19)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (33, N'ETHN', 20)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (34, N'FPM', 21)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (35, N'HLAW', 22)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (36, N'HITO', 23)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (37, N'HIEA', 23)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (38, N'HIEU', 23)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (39, N'HILA', 23)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (40, N'HINE', 23)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (41, N'HIUS', 23)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (42, N'HILD', 23)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (43, N'HDP', 24)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (44, N'HUM', 25)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (45, N'INTL', 26)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (46, N'LATI', 27)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (47, N'LAWS', 28)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (48, N'LHCO', 29)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (49, N'LIFR', 30)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (50, N'LIGN', 30)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (51, N'LIGM', 30)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (52, N'LISP', 30)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (53, N'LTAM', 62)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (54, N'LTCS', 62)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (55, N'LTFR', 62)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (56, N'LTSP', 62)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (57, N'LTEN', 62)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (58, N'LTWL', 62)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (59, N'MMW', 31)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (60, N'MATH', 32)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (61, N'MAE', 33)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (62, N'MED', 34)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (63, N'MCWP', 35)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (64, N'MUS', 36)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (65, N'NANO', 37)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (66, N'OPTH', 38)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (67, N'PATH', 39)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (68, N'PEDS', 40)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (69, N'PHIL', 41)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (70, N'PHYS', 42)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (71, N'POLI', 43)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (72, N'PSYC', 44)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (73, N'RAD', 46)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (74, N'MGTF', 47)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (75, N'RELI', 48)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (76, N'RMED', 63)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (77, N'SOMI', 49)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (78, N'SIOC', 51)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (79, N'SIO', 51)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (80, N'SOCI', 52)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (81, N'SE', 53)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (82, N'SURG', 54)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (83, N'TDAC', 55)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (84, N'TDDE', 55)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (85, N'TDGE', 55)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (86, N'TDHT', 55)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (87, N'TDPW', 55)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (88, N'TWS', 56)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (89, N'USP', 58)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (90, N'VIS', 59)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (91, N'WCWP', 60)
INSERT [dbo].[COURSE_SUBJECT] ([subject_id], [symbol], [dept_id]) VALUES (92, N'WES', 61)
SET IDENTITY_INSERT [dbo].[COURSE_SUBJECT] OFF
SET IDENTITY_INSERT [dbo].[COURSE_TYPE] ON 

INSERT [dbo].[COURSE_TYPE] ([id], [name]) VALUES (1, N'Lower Division')
INSERT [dbo].[COURSE_TYPE] ([id], [name]) VALUES (2, N'Upper Division')
INSERT [dbo].[COURSE_TYPE] ([id], [name]) VALUES (3, N'Graduate')
INSERT [dbo].[COURSE_TYPE] ([id], [name]) VALUES (4, N'Tech Elective')
SET IDENTITY_INSERT [dbo].[COURSE_TYPE] OFF
SET IDENTITY_INSERT [dbo].[DEGREE_TYPE] ON 

INSERT [dbo].[DEGREE_TYPE] ([id], [name]) VALUES (1, N'Bachelors')
INSERT [dbo].[DEGREE_TYPE] ([id], [name]) VALUES (2, N'Master')
INSERT [dbo].[DEGREE_TYPE] ([id], [name]) VALUES (3, N'PhD')
SET IDENTITY_INSERT [dbo].[DEGREE_TYPE] OFF
SET IDENTITY_INSERT [dbo].[DEPARTMENT] ON 

INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (1, N'Academic Internship Program')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (2, N'Anesthesiology')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (3, N'Anthropology')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (4, N'Arts and Humanities')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (5, N'Biology')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (6, N'Chemistry and Biochemistry')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (7, N'Clinical Research Program')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (8, N'Cognitive Science')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (9, N'Communication')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (10, N'Computer Science & Engineering')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (11, N'Contemporary Issues Program')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (12, N'Critical Gender Studies Program')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (13, N'Cultuture, Art & Technology Program')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (14, N'Dimensions of Culture Program')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (15, N'Economics')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (16, N'Education Studies')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (17, N'Electrical & Computer Engineering')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (18, N'Emergency Medicine')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (19, N'Environmental Studies Program')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (20, N'Ethnic Studies')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (21, N'Family and Preventive Medicine')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (22, N'Health Law Program')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (23, N'History')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (24, N'Human Development Program')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (25, N'Humanities Program')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (26, N'International Studies Program')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (27, N'Latin American Studies Program')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (28, N'Law and Society Program')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (29, N'Leadership/Health Care Organization')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (30, N'Linguistics')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (31, N'Making of the Modern World')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (32, N'Mathematics')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (33, N'Mechanical & Aerospace Engineering')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (34, N'Medicine')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (35, N'Muir College Writing Program')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (36, N'Music')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (37, N'NanoEngineering')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (38, N'Ophthalmology')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (39, N'Pathology')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (40, N'Pediatrics')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (41, N'Phillosophy')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (42, N'Physics')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (43, N'Political Science')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (44, N'Psychiatry')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (45, N'Psychology')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (46, N'Radiology')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (47, N'Rady School of Management')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (48, N'Religion, Program for the Study of Reproductive Medicine')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (49, N'Sch of Med Interdisciplinary Crses')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (50, N'School of Engineering')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (51, N'Scripps Institution of Oceanography')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (52, N'Sociology')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (53, N'Structural Engineering')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (54, N'Surgery')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (55, N'Theatre and Dance')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (56, N'Third World Studies Program')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (57, N'Unaffiliated')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (58, N'Urban Studies & Planning Program')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (59, N'Visual Arts')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (60, N'Warren College Writing Program')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (61, N'Wireless Embedded Systems')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (62, N'Literature')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (63, N'Reproductive Medicine')
INSERT [dbo].[DEPARTMENT] ([id], [name]) VALUES (64, N'African American Studies')
SET IDENTITY_INSERT [dbo].[DEPARTMENT] OFF
INSERT [dbo].[GRADE_CONVERSION] ([letter_grade], [number_grade]) VALUES (N'A+', CAST(4.3 AS Decimal(2, 1)))
INSERT [dbo].[GRADE_CONVERSION] ([letter_grade], [number_grade]) VALUES (N'A', CAST(4.0 AS Decimal(2, 1)))
INSERT [dbo].[GRADE_CONVERSION] ([letter_grade], [number_grade]) VALUES (N'A-', CAST(3.7 AS Decimal(2, 1)))
INSERT [dbo].[GRADE_CONVERSION] ([letter_grade], [number_grade]) VALUES (N'B+', CAST(3.4 AS Decimal(2, 1)))
INSERT [dbo].[GRADE_CONVERSION] ([letter_grade], [number_grade]) VALUES (N'B', CAST(3.1 AS Decimal(2, 1)))
INSERT [dbo].[GRADE_CONVERSION] ([letter_grade], [number_grade]) VALUES (N'B-', CAST(2.8 AS Decimal(2, 1)))
INSERT [dbo].[GRADE_CONVERSION] ([letter_grade], [number_grade]) VALUES (N'C+', CAST(2.5 AS Decimal(2, 1)))
INSERT [dbo].[GRADE_CONVERSION] ([letter_grade], [number_grade]) VALUES (N'C', CAST(2.2 AS Decimal(2, 1)))
INSERT [dbo].[GRADE_CONVERSION] ([letter_grade], [number_grade]) VALUES (N'C-', CAST(1.9 AS Decimal(2, 1)))
INSERT [dbo].[GRADE_CONVERSION] ([letter_grade], [number_grade]) VALUES (N'D', CAST(1.6 AS Decimal(2, 1)))
SET IDENTITY_INSERT [dbo].[QUARTER] ON 

INSERT [dbo].[QUARTER] ([id], [name_id], [year]) VALUES (1, 1, 2016)
INSERT [dbo].[QUARTER] ([id], [name_id], [year]) VALUES (2, 2, 2017)
INSERT [dbo].[QUARTER] ([id], [name_id], [year]) VALUES (3, 3, 2017)
SET IDENTITY_INSERT [dbo].[QUARTER] OFF
SET IDENTITY_INSERT [dbo].[QUARTER_NAME] ON 

INSERT [dbo].[QUARTER_NAME] ([id], [name]) VALUES (1, N'FALL')
INSERT [dbo].[QUARTER_NAME] ([id], [name]) VALUES (2, N'WINTER')
INSERT [dbo].[QUARTER_NAME] ([id], [name]) VALUES (3, N'SPRING')
SET IDENTITY_INSERT [dbo].[QUARTER_NAME] OFF
SET IDENTITY_INSERT [dbo].[RESIDENT_STATUS] ON 

INSERT [dbo].[RESIDENT_STATUS] ([id], [type]) VALUES (1, N'CA')
INSERT [dbo].[RESIDENT_STATUS] ([id], [type]) VALUES (2, N'NON-CA')
INSERT [dbo].[RESIDENT_STATUS] ([id], [type]) VALUES (3, N'FOREIGN')
SET IDENTITY_INSERT [dbo].[RESIDENT_STATUS] OFF
/****** Object:  Index [IX_CLASS]    Script Date: 5/20/2017 11:35:48 PM ******/
ALTER TABLE [dbo].[CLASS] ADD  CONSTRAINT [IX_CLASS] UNIQUE NONCLUSTERED 
(
	[course_id] ASC,
	[quarter_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
/****** Object:  Index [IX_COMMITTEE_FACULTY]    Script Date: 5/20/2017 11:35:48 PM ******/
ALTER TABLE [dbo].[COMMITTEE_FACULTY] ADD  CONSTRAINT [IX_COMMITTEE_FACULTY] UNIQUE NONCLUSTERED 
(
	[committee_id] ASC,
	[faculty_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
/****** Object:  Index [IX_PROBATION_PERIOD]    Script Date: 5/20/2017 11:35:48 PM ******/
ALTER TABLE [dbo].[PROBATION_PERIOD] ADD  CONSTRAINT [IX_PROBATION_PERIOD] UNIQUE NONCLUSTERED 
(
	[quarter_id] ASC,
	[student_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
/****** Object:  Index [IX_QUARTER]    Script Date: 5/20/2017 11:35:48 PM ******/
ALTER TABLE [dbo].[QUARTER] ADD  CONSTRAINT [IX_QUARTER] UNIQUE NONCLUSTERED 
(
	[name_id] ASC,
	[year] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
/****** Object:  Index [IX_STUDENT]    Script Date: 5/20/2017 11:35:48 PM ******/
ALTER TABLE [dbo].[STUDENT] ADD  CONSTRAINT [IX_STUDENT] UNIQUE NONCLUSTERED 
(
	[pid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
/****** Object:  Index [IX_STUDENT_1]    Script Date: 5/20/2017 11:35:48 PM ******/
ALTER TABLE [dbo].[STUDENT] ADD  CONSTRAINT [IX_STUDENT_1] UNIQUE NONCLUSTERED 
(
	[ssn] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
/****** Object:  Index [IX_UNIT_REQUIREMENT]    Script Date: 5/20/2017 11:35:48 PM ******/
ALTER TABLE [dbo].[UNIT_REQUIREMENT] ADD  CONSTRAINT [IX_UNIT_REQUIREMENT] UNIQUE NONCLUSTERED 
(
	[degree_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[ATTENDANCE]  WITH CHECK ADD  CONSTRAINT [FK_ATTENDANCE_QUARTER] FOREIGN KEY([quarter_id])
REFERENCES [dbo].[QUARTER] ([id])
GO
ALTER TABLE [dbo].[ATTENDANCE] CHECK CONSTRAINT [FK_ATTENDANCE_QUARTER]
GO
ALTER TABLE [dbo].[ATTENDANCE]  WITH CHECK ADD  CONSTRAINT [FK_ATTENDANCE_STUDENT] FOREIGN KEY([student_id])
REFERENCES [dbo].[STUDENT] ([id])
GO
ALTER TABLE [dbo].[ATTENDANCE] CHECK CONSTRAINT [FK_ATTENDANCE_STUDENT]
GO
ALTER TABLE [dbo].[BOOK_AVAILABILITY]  WITH CHECK ADD  CONSTRAINT [FK_BOOK_AVAILABILITY_BOOK] FOREIGN KEY([book_id])
REFERENCES [dbo].[BOOK] ([id])
GO
ALTER TABLE [dbo].[BOOK_AVAILABILITY] CHECK CONSTRAINT [FK_BOOK_AVAILABILITY_BOOK]
GO
ALTER TABLE [dbo].[BOOK_AVAILABILITY]  WITH CHECK ADD  CONSTRAINT [FK_BOOK_AVAILABILITY_BOOK_CONDITION] FOREIGN KEY([condition_id])
REFERENCES [dbo].[BOOK_CONDITION] ([id])
GO
ALTER TABLE [dbo].[BOOK_AVAILABILITY] CHECK CONSTRAINT [FK_BOOK_AVAILABILITY_BOOK_CONDITION]
GO
ALTER TABLE [dbo].[BSMS_STUDENT]  WITH CHECK ADD  CONSTRAINT [FK_BSMS_STUDENT_COLLEGE] FOREIGN KEY([college_id])
REFERENCES [dbo].[COLLEGE] ([id])
GO
ALTER TABLE [dbo].[BSMS_STUDENT] CHECK CONSTRAINT [FK_BSMS_STUDENT_COLLEGE]
GO
ALTER TABLE [dbo].[BSMS_STUDENT]  WITH CHECK ADD  CONSTRAINT [FK_BSMS_STUDENT_DEPARTMENT] FOREIGN KEY([major_id])
REFERENCES [dbo].[DEPARTMENT] ([id])
GO
ALTER TABLE [dbo].[BSMS_STUDENT] CHECK CONSTRAINT [FK_BSMS_STUDENT_DEPARTMENT]
GO
ALTER TABLE [dbo].[BSMS_STUDENT]  WITH CHECK ADD  CONSTRAINT [FK_BSMS_STUDENT_DEPARTMENT1] FOREIGN KEY([minor_id])
REFERENCES [dbo].[DEPARTMENT] ([id])
GO
ALTER TABLE [dbo].[BSMS_STUDENT] CHECK CONSTRAINT [FK_BSMS_STUDENT_DEPARTMENT1]
GO
ALTER TABLE [dbo].[BSMS_STUDENT]  WITH CHECK ADD  CONSTRAINT [FK_BSMS_STUDENT_MASTER] FOREIGN KEY([id])
REFERENCES [dbo].[MASTER] ([id])
GO
ALTER TABLE [dbo].[BSMS_STUDENT] CHECK CONSTRAINT [FK_BSMS_STUDENT_MASTER]
GO
ALTER TABLE [dbo].[CLASS]  WITH CHECK ADD  CONSTRAINT [FK_CLASS_COURSE] FOREIGN KEY([course_id])
REFERENCES [dbo].[COURSE] ([id])
GO
ALTER TABLE [dbo].[CLASS] CHECK CONSTRAINT [FK_CLASS_COURSE]
GO
ALTER TABLE [dbo].[CLASS]  WITH CHECK ADD  CONSTRAINT [FK_CLASS_QUARTER] FOREIGN KEY([quarter_id])
REFERENCES [dbo].[QUARTER] ([id])
GO
ALTER TABLE [dbo].[CLASS] CHECK CONSTRAINT [FK_CLASS_QUARTER]
GO
ALTER TABLE [dbo].[COMMITTEE_FACULTY]  WITH CHECK ADD  CONSTRAINT [FK_COMMITTEE_FACULTY_COMMITTEE] FOREIGN KEY([committee_id])
REFERENCES [dbo].[COMMITTEE] ([id])
GO
ALTER TABLE [dbo].[COMMITTEE_FACULTY] CHECK CONSTRAINT [FK_COMMITTEE_FACULTY_COMMITTEE]
GO
ALTER TABLE [dbo].[COMMITTEE_FACULTY]  WITH CHECK ADD  CONSTRAINT [FK_COMMITTEE_FACULTY_FACULTY] FOREIGN KEY([faculty_id])
REFERENCES [dbo].[FACULTY] ([id])
GO
ALTER TABLE [dbo].[COMMITTEE_FACULTY] CHECK CONSTRAINT [FK_COMMITTEE_FACULTY_FACULTY]
GO
ALTER TABLE [dbo].[COMMITTEE_GRAD]  WITH CHECK ADD  CONSTRAINT [FK_COMMITTEE_GRAD_COMMITTEE] FOREIGN KEY([committee_id])
REFERENCES [dbo].[COMMITTEE] ([id])
GO
ALTER TABLE [dbo].[COMMITTEE_GRAD] CHECK CONSTRAINT [FK_COMMITTEE_GRAD_COMMITTEE]
GO
ALTER TABLE [dbo].[COMMITTEE_GRAD]  WITH CHECK ADD  CONSTRAINT [FK_COMMITTEE_GRAD_GRAD] FOREIGN KEY([grad_id])
REFERENCES [dbo].[GRAD] ([id])
GO
ALTER TABLE [dbo].[COMMITTEE_GRAD] CHECK CONSTRAINT [FK_COMMITTEE_GRAD_GRAD]
GO
ALTER TABLE [dbo].[COMMITTEE_PHD]  WITH CHECK ADD  CONSTRAINT [FK_COMMITTEE_PHD_COMMITTEE] FOREIGN KEY([committee_id])
REFERENCES [dbo].[COMMITTEE] ([id])
GO
ALTER TABLE [dbo].[COMMITTEE_PHD] CHECK CONSTRAINT [FK_COMMITTEE_PHD_COMMITTEE]
GO
ALTER TABLE [dbo].[COMMITTEE_PHD]  WITH CHECK ADD  CONSTRAINT [FK_COMMITTEE_PHD_PHD_CANDIDATE] FOREIGN KEY([phd_candidate_id])
REFERENCES [dbo].[PHD_CANDIDATE] ([id])
GO
ALTER TABLE [dbo].[COMMITTEE_PHD] CHECK CONSTRAINT [FK_COMMITTEE_PHD_PHD_CANDIDATE]
GO
ALTER TABLE [dbo].[COURSE]  WITH CHECK ADD  CONSTRAINT [FK_COURSE_COURSE_SUBJECT] FOREIGN KEY([subject_id])
REFERENCES [dbo].[COURSE_SUBJECT] ([subject_id])
GO
ALTER TABLE [dbo].[COURSE] CHECK CONSTRAINT [FK_COURSE_COURSE_SUBJECT]
GO
ALTER TABLE [dbo].[COURSE_CATEGORY_REQUIREMENT]  WITH CHECK ADD  CONSTRAINT [FK_COURSE_CATEGORY_REQUIREMENT_COURSE_TYPE] FOREIGN KEY([type_id])
REFERENCES [dbo].[COURSE_TYPE] ([id])
GO
ALTER TABLE [dbo].[COURSE_CATEGORY_REQUIREMENT] CHECK CONSTRAINT [FK_COURSE_CATEGORY_REQUIREMENT_COURSE_TYPE]
GO
ALTER TABLE [dbo].[COURSE_SUBJECT]  WITH CHECK ADD  CONSTRAINT [FK_COURSE_SUBJECT_DEPARTMENT] FOREIGN KEY([dept_id])
REFERENCES [dbo].[DEPARTMENT] ([id])
GO
ALTER TABLE [dbo].[COURSE_SUBJECT] CHECK CONSTRAINT [FK_COURSE_SUBJECT_DEPARTMENT]
GO
ALTER TABLE [dbo].[COURSE_TYPE_CATEGORY]  WITH CHECK ADD  CONSTRAINT [FK_COURSE_TYPE_CATEGORY_COURSE] FOREIGN KEY([course_id])
REFERENCES [dbo].[COURSE] ([id])
GO
ALTER TABLE [dbo].[COURSE_TYPE_CATEGORY] CHECK CONSTRAINT [FK_COURSE_TYPE_CATEGORY_COURSE]
GO
ALTER TABLE [dbo].[COURSE_TYPE_CATEGORY]  WITH CHECK ADD  CONSTRAINT [FK_COURSE_TYPE_CATEGORY_COURSE_TYPE] FOREIGN KEY([type_id])
REFERENCES [dbo].[COURSE_TYPE] ([id])
GO
ALTER TABLE [dbo].[COURSE_TYPE_CATEGORY] CHECK CONSTRAINT [FK_COURSE_TYPE_CATEGORY_COURSE_TYPE]
GO
ALTER TABLE [dbo].[DEGREE]  WITH CHECK ADD  CONSTRAINT [FK_DEGREE_DEGREE_TYPE] FOREIGN KEY([type_id])
REFERENCES [dbo].[DEGREE_TYPE] ([id])
GO
ALTER TABLE [dbo].[DEGREE] CHECK CONSTRAINT [FK_DEGREE_DEGREE_TYPE]
GO
ALTER TABLE [dbo].[DISCUSSION]  WITH CHECK ADD  CONSTRAINT [FK_DISCUSSION_WEEKLY_MEETING] FOREIGN KEY([id])
REFERENCES [dbo].[WEEKLY_MEETING] ([id])
GO
ALTER TABLE [dbo].[DISCUSSION] CHECK CONSTRAINT [FK_DISCUSSION_WEEKLY_MEETING]
GO
ALTER TABLE [dbo].[ENROLLMENT]  WITH CHECK ADD  CONSTRAINT [FK_ENROLLMENT_SECTION] FOREIGN KEY([section_id])
REFERENCES [dbo].[SECTION] ([id])
GO
ALTER TABLE [dbo].[ENROLLMENT] CHECK CONSTRAINT [FK_ENROLLMENT_SECTION]
GO
ALTER TABLE [dbo].[ENROLLMENT]  WITH CHECK ADD  CONSTRAINT [FK_ENROLLMENT_STUDENT] FOREIGN KEY([student_id])
REFERENCES [dbo].[STUDENT] ([id])
GO
ALTER TABLE [dbo].[ENROLLMENT] CHECK CONSTRAINT [FK_ENROLLMENT_STUDENT]
GO
ALTER TABLE [dbo].[FACULTY]  WITH CHECK ADD  CONSTRAINT [FK_FACULTY_DEPARTMENT] FOREIGN KEY([dept_id])
REFERENCES [dbo].[DEPARTMENT] ([id])
GO
ALTER TABLE [dbo].[FACULTY] CHECK CONSTRAINT [FK_FACULTY_DEPARTMENT]
GO
ALTER TABLE [dbo].[GRAD]  WITH CHECK ADD  CONSTRAINT [FK_GRAD_DEPARTMENT] FOREIGN KEY([dept_id])
REFERENCES [dbo].[DEPARTMENT] ([id])
GO
ALTER TABLE [dbo].[GRAD] CHECK CONSTRAINT [FK_GRAD_DEPARTMENT]
GO
ALTER TABLE [dbo].[GRAD]  WITH CHECK ADD  CONSTRAINT [FK_GRAD_STUDENT] FOREIGN KEY([id])
REFERENCES [dbo].[STUDENT] ([id])
GO
ALTER TABLE [dbo].[GRAD] CHECK CONSTRAINT [FK_GRAD_STUDENT]
GO
ALTER TABLE [dbo].[MASTER]  WITH CHECK ADD  CONSTRAINT [FK_MASTER_GRAD] FOREIGN KEY([id])
REFERENCES [dbo].[GRAD] ([id])
GO
ALTER TABLE [dbo].[MASTER] CHECK CONSTRAINT [FK_MASTER_GRAD]
GO
ALTER TABLE [dbo].[MEETING]  WITH CHECK ADD  CONSTRAINT [FK_MEETING_SECTION] FOREIGN KEY([section_id])
REFERENCES [dbo].[SECTION] ([id])
GO
ALTER TABLE [dbo].[MEETING] CHECK CONSTRAINT [FK_MEETING_SECTION]
GO
ALTER TABLE [dbo].[MS_CONCENTRATION]  WITH CHECK ADD  CONSTRAINT [FK_MS_CONCENTRATION_COURSE] FOREIGN KEY([course_id])
REFERENCES [dbo].[COURSE] ([id])
GO
ALTER TABLE [dbo].[MS_CONCENTRATION] CHECK CONSTRAINT [FK_MS_CONCENTRATION_COURSE]
GO
ALTER TABLE [dbo].[MS_CONCENTRATION]  WITH CHECK ADD  CONSTRAINT [FK_MS_CONCENTRATION_DEGREE] FOREIGN KEY([degree_id])
REFERENCES [dbo].[DEGREE] ([id])
GO
ALTER TABLE [dbo].[MS_CONCENTRATION] CHECK CONSTRAINT [FK_MS_CONCENTRATION_DEGREE]
GO
ALTER TABLE [dbo].[NON_DISCUSSION]  WITH CHECK ADD  CONSTRAINT [FK_NON_DISCUSSION_WEEKLY_MEETING] FOREIGN KEY([id])
REFERENCES [dbo].[WEEKLY_MEETING] ([id])
GO
ALTER TABLE [dbo].[NON_DISCUSSION] CHECK CONSTRAINT [FK_NON_DISCUSSION_WEEKLY_MEETING]
GO
ALTER TABLE [dbo].[PHD]  WITH CHECK ADD  CONSTRAINT [FK_PHD_GRAD] FOREIGN KEY([id])
REFERENCES [dbo].[GRAD] ([id])
GO
ALTER TABLE [dbo].[PHD] CHECK CONSTRAINT [FK_PHD_GRAD]
GO
ALTER TABLE [dbo].[PHD_CANDIDATE]  WITH CHECK ADD  CONSTRAINT [FK_PHD_CANDIDATE_FACULTY] FOREIGN KEY([advisor_id])
REFERENCES [dbo].[FACULTY] ([id])
GO
ALTER TABLE [dbo].[PHD_CANDIDATE] CHECK CONSTRAINT [FK_PHD_CANDIDATE_FACULTY]
GO
ALTER TABLE [dbo].[PHD_CANDIDATE]  WITH CHECK ADD  CONSTRAINT [FK_PHD_CANDIDATE_PHD] FOREIGN KEY([id])
REFERENCES [dbo].[PHD] ([id])
GO
ALTER TABLE [dbo].[PHD_CANDIDATE] CHECK CONSTRAINT [FK_PHD_CANDIDATE_PHD]
GO
ALTER TABLE [dbo].[PHD_PRECANDIDATE]  WITH CHECK ADD  CONSTRAINT [FK_PHD_PRECANDIDATE_PHD] FOREIGN KEY([id])
REFERENCES [dbo].[PHD] ([id])
GO
ALTER TABLE [dbo].[PHD_PRECANDIDATE] CHECK CONSTRAINT [FK_PHD_PRECANDIDATE_PHD]
GO
ALTER TABLE [dbo].[PREREQUISITE]  WITH CHECK ADD  CONSTRAINT [FK_PREREQUISITE_COURSE] FOREIGN KEY([course_id])
REFERENCES [dbo].[COURSE] ([id])
GO
ALTER TABLE [dbo].[PREREQUISITE] CHECK CONSTRAINT [FK_PREREQUISITE_COURSE]
GO
ALTER TABLE [dbo].[PREREQUISITE]  WITH CHECK ADD  CONSTRAINT [FK_PREREQUISITE_COURSE1] FOREIGN KEY([prereq_id])
REFERENCES [dbo].[COURSE] ([id])
GO
ALTER TABLE [dbo].[PREREQUISITE] CHECK CONSTRAINT [FK_PREREQUISITE_COURSE1]
GO
ALTER TABLE [dbo].[PROBATION_PERIOD]  WITH CHECK ADD  CONSTRAINT [FK_PROBATION_PERIOD_QUARTER] FOREIGN KEY([quarter_id])
REFERENCES [dbo].[QUARTER] ([id])
GO
ALTER TABLE [dbo].[PROBATION_PERIOD] CHECK CONSTRAINT [FK_PROBATION_PERIOD_QUARTER]
GO
ALTER TABLE [dbo].[PROBATION_PERIOD]  WITH CHECK ADD  CONSTRAINT [FK_PROBATION_PERIOD_STUDENT] FOREIGN KEY([student_id])
REFERENCES [dbo].[STUDENT] ([id])
GO
ALTER TABLE [dbo].[PROBATION_PERIOD] CHECK CONSTRAINT [FK_PROBATION_PERIOD_STUDENT]
GO
ALTER TABLE [dbo].[QUARTER]  WITH CHECK ADD  CONSTRAINT [FK_QUARTER_QUARTER_NAME] FOREIGN KEY([name_id])
REFERENCES [dbo].[QUARTER_NAME] ([id])
GO
ALTER TABLE [dbo].[QUARTER] CHECK CONSTRAINT [FK_QUARTER_QUARTER_NAME]
GO
ALTER TABLE [dbo].[REVIEW_SESSION]  WITH CHECK ADD  CONSTRAINT [FK_REVIEW_SESSION_MEETING] FOREIGN KEY([id])
REFERENCES [dbo].[MEETING] ([id])
GO
ALTER TABLE [dbo].[REVIEW_SESSION] CHECK CONSTRAINT [FK_REVIEW_SESSION_MEETING]
GO
ALTER TABLE [dbo].[SECTION]  WITH CHECK ADD  CONSTRAINT [FK_SECTION_CLASS] FOREIGN KEY([class_id])
REFERENCES [dbo].[CLASS] ([id])
GO
ALTER TABLE [dbo].[SECTION] CHECK CONSTRAINT [FK_SECTION_CLASS]
GO
ALTER TABLE [dbo].[SECTION]  WITH CHECK ADD  CONSTRAINT [FK_SECTION_FACULTY] FOREIGN KEY([faculty_id])
REFERENCES [dbo].[FACULTY] ([id])
GO
ALTER TABLE [dbo].[SECTION] CHECK CONSTRAINT [FK_SECTION_FACULTY]
GO
ALTER TABLE [dbo].[SECTION_BOOKLIST]  WITH CHECK ADD  CONSTRAINT [FK_SECTION_BOOKLIST_BOOK] FOREIGN KEY([book_id])
REFERENCES [dbo].[BOOK] ([id])
GO
ALTER TABLE [dbo].[SECTION_BOOKLIST] CHECK CONSTRAINT [FK_SECTION_BOOKLIST_BOOK]
GO
ALTER TABLE [dbo].[SECTION_BOOKLIST]  WITH CHECK ADD  CONSTRAINT [FK_SECTION_BOOKLIST_SECTION] FOREIGN KEY([section_id])
REFERENCES [dbo].[SECTION] ([id])
GO
ALTER TABLE [dbo].[SECTION_BOOKLIST] CHECK CONSTRAINT [FK_SECTION_BOOKLIST_SECTION]
GO
ALTER TABLE [dbo].[STUDENT]  WITH CHECK ADD  CONSTRAINT [FK_STUDENT_RESIDENT_STATUS] FOREIGN KEY([resident_status])
REFERENCES [dbo].[RESIDENT_STATUS] ([id])
GO
ALTER TABLE [dbo].[STUDENT] CHECK CONSTRAINT [FK_STUDENT_RESIDENT_STATUS]
GO
ALTER TABLE [dbo].[UNDERGRAD]  WITH CHECK ADD  CONSTRAINT [FK_UNDERGRAD_COLLEGE] FOREIGN KEY([college_id])
REFERENCES [dbo].[COLLEGE] ([id])
GO
ALTER TABLE [dbo].[UNDERGRAD] CHECK CONSTRAINT [FK_UNDERGRAD_COLLEGE]
GO
ALTER TABLE [dbo].[UNDERGRAD]  WITH CHECK ADD  CONSTRAINT [FK_UNDERGRAD_DEPARTMENT] FOREIGN KEY([minor_id])
REFERENCES [dbo].[DEPARTMENT] ([id])
GO
ALTER TABLE [dbo].[UNDERGRAD] CHECK CONSTRAINT [FK_UNDERGRAD_DEPARTMENT]
GO
ALTER TABLE [dbo].[UNDERGRAD]  WITH CHECK ADD  CONSTRAINT [FK_UNDERGRAD_DEPARTMENT1] FOREIGN KEY([major_id])
REFERENCES [dbo].[DEPARTMENT] ([id])
GO
ALTER TABLE [dbo].[UNDERGRAD] CHECK CONSTRAINT [FK_UNDERGRAD_DEPARTMENT1]
GO
ALTER TABLE [dbo].[UNDERGRAD]  WITH CHECK ADD  CONSTRAINT [FK_UNDERGRAD_STUDENT] FOREIGN KEY([id])
REFERENCES [dbo].[STUDENT] ([id])
GO
ALTER TABLE [dbo].[UNDERGRAD] CHECK CONSTRAINT [FK_UNDERGRAD_STUDENT]
GO
ALTER TABLE [dbo].[UNIT_COURSE_CATEGORY]  WITH CHECK ADD  CONSTRAINT [FK_UNIT_COURSE_CATEGORY_COURSE_CATEGORY_REQUIREMENT] FOREIGN KEY([course_category_id])
REFERENCES [dbo].[COURSE_CATEGORY_REQUIREMENT] ([id])
GO
ALTER TABLE [dbo].[UNIT_COURSE_CATEGORY] CHECK CONSTRAINT [FK_UNIT_COURSE_CATEGORY_COURSE_CATEGORY_REQUIREMENT]
GO
ALTER TABLE [dbo].[UNIT_COURSE_CATEGORY]  WITH CHECK ADD  CONSTRAINT [FK_UNIT_COURSE_CATEGORY_UNIT_CATEGORY] FOREIGN KEY([unit_category_id])
REFERENCES [dbo].[UNIT_CATEGORY] ([id])
GO
ALTER TABLE [dbo].[UNIT_COURSE_CATEGORY] CHECK CONSTRAINT [FK_UNIT_COURSE_CATEGORY_UNIT_CATEGORY]
GO
ALTER TABLE [dbo].[UNIT_REQUIREMENT]  WITH CHECK ADD  CONSTRAINT [FK_UNIT_REQUIREMENT_DEGREE] FOREIGN KEY([degree_id])
REFERENCES [dbo].[DEGREE] ([id])
GO
ALTER TABLE [dbo].[UNIT_REQUIREMENT] CHECK CONSTRAINT [FK_UNIT_REQUIREMENT_DEGREE]
GO
ALTER TABLE [dbo].[UNIT_REQUIREMENT]  WITH CHECK ADD  CONSTRAINT [FK_UNIT_REQUIREMENT_DEPARTMENT] FOREIGN KEY([department_id])
REFERENCES [dbo].[DEPARTMENT] ([id])
GO
ALTER TABLE [dbo].[UNIT_REQUIREMENT] CHECK CONSTRAINT [FK_UNIT_REQUIREMENT_DEPARTMENT]
GO
ALTER TABLE [dbo].[UNIT_REQUIREMENT]  WITH CHECK ADD  CONSTRAINT [FK_UNIT_REQUIREMENT_UNIT_CATEGORY] FOREIGN KEY([totalunit_id])
REFERENCES [dbo].[UNIT_CATEGORY] ([id])
GO
ALTER TABLE [dbo].[UNIT_REQUIREMENT] CHECK CONSTRAINT [FK_UNIT_REQUIREMENT_UNIT_CATEGORY]
GO
ALTER TABLE [dbo].[WAITLIST]  WITH CHECK ADD  CONSTRAINT [FK_WAITLIST_SECTION] FOREIGN KEY([section_id])
REFERENCES [dbo].[SECTION] ([id])
GO
ALTER TABLE [dbo].[WAITLIST] CHECK CONSTRAINT [FK_WAITLIST_SECTION]
GO
ALTER TABLE [dbo].[WAITLIST]  WITH CHECK ADD  CONSTRAINT [FK_WAITLIST_STUDENT] FOREIGN KEY([student_id])
REFERENCES [dbo].[STUDENT] ([id])
GO
ALTER TABLE [dbo].[WAITLIST] CHECK CONSTRAINT [FK_WAITLIST_STUDENT]
GO
ALTER TABLE [dbo].[WEEKLY_MEETING]  WITH CHECK ADD  CONSTRAINT [FK_WEEKLY_MEETING_MEETING] FOREIGN KEY([id])
REFERENCES [dbo].[MEETING] ([id])
GO
ALTER TABLE [dbo].[WEEKLY_MEETING] CHECK CONSTRAINT [FK_WEEKLY_MEETING_MEETING]
GO
ALTER TABLE [dbo].[UNDERGRAD]  WITH CHECK ADD  CONSTRAINT [CK_UNDERGRAD] CHECK  (([major_id]<>[minor_id]))
GO
ALTER TABLE [dbo].[UNDERGRAD] CHECK CONSTRAINT [CK_UNDERGRAD]
GO
