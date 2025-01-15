<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TECHNOVA INSTITUTE</title>
    <link rel="icon" type="image/x-icon" href="upload/logo.png">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<% String msg = request.getParameter("msg");
String err = request.getParameter("error"); 
if (err!=null){%><script>alert("<%=err%>")</script><%}
if (msg!=null){%><script>alert("<%=msg%>")</script><%}
%>
<header>
        <div class="logo">
            <div id="top-img">
                <img src="upload/logo.png" alt="college Logo">
                <h1>TECHNOVA INSTITUTE</h1>
                <div id="contact">
                    <h4><svg xmlns="http://www.w3.org/2000/svg" width="17" height="17" fill="currentColor" class="bi bi-envelope" viewBox="0 0 16 16">
                        <path d="M0 4a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2zm2-1a1 1 0 0 0-1 1v.217l7 4.2 7-4.2V4a1 1 0 0 0-1-1zm13 2.383-4.708 2.825L15 11.105zm-.034 6.876-5.64-3.471L8 9.583l-1.326-.795-5.64 3.47A1 1 0 0 0 2 13h12a1 1 0 0 0 .966-.741M1 11.105l4.708-2.897L1 5.383z"/>
                      </svg>in.technovainstitute@gmail.com</h4>  
                      <h4><svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-telephone-fill" viewBox="0 0 16 16">
                        <path fill-rule="evenodd" d="M1.885.511a1.745 1.745 0 0 1 2.61.163L6.29 2.98c.329.423.445.974.315 1.494l-.547 2.19a.68.68 0 0 0 .178.643l2.457 2.457a.68.68 0 0 0 .644.178l2.189-.547a1.75 1.75 0 0 1 1.494.315l2.306 1.794c.829.645.905 1.87.163 2.611l-1.034 1.034c-.74.74-1.846 1.065-2.877.702a18.6 18.6 0 0 1-7.01-4.42 18.6 18.6 0 0 1-4.42-7.009c-.362-1.03-.037-2.137.703-2.877z"/>
                      </svg>+91 123456789</h4>
                </div>
            </div>
            <nav>
                <ul>
                    <li class="active"><a href=""><svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-house-door" viewBox="0 0 16 16">
                        <path d="M8.354 1.146a.5.5 0 0 0-.708 0l-6 6A.5.5 0 0 0 1.5 7.5v7a.5.5 0 0 0 .5.5h4.5a.5.5 0 0 0 .5-.5v-4h2v4a.5.5 0 0 0 .5.5H14a.5.5 0 0 0 .5-.5v-7a.5.5 0 0 0-.146-.354L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293zM2.5 14V7.707l5.5-5.5 5.5 5.5V14H10v-4a.5.5 0 0 0-.5-.5h-3a.5.5 0 0 0-.5.5v4z"/>
                      </svg>Home</a></li>
                    <li class="active"><a href="Events.html"><svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-calendar-event" viewBox="0 0 16 16">
                        <path d="M11 6.5a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-1a.5.5 0 0 1-.5-.5z"/>
                        <path d="M3.5 0a.5.5 0 0 1 .5.5V1h8V.5a.5.5 0 0 1 1 0V1h1a2 2 0 0 1 2 2v11a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h1V.5a.5.5 0 0 1 .5-.5M1 4v10a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4z"/>
                      </svg>Events</a></li>
                    <li class="active"><a href="#coursesSection"><svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-bookmark" viewBox="0 0 16 16">
                        <path d="M2 2a2 2 0 0 1 2-2h8a2 2 0 0 1 2 2v13.5a.5.5 0 0 1-.777.416L8 13.101l-5.223 2.815A.5.5 0 0 1 2 15.5zm2-1a1 1 0 0 0-1 1v12.566l4.723-2.482a.5.5 0 0 1 .554 0L13 14.566V2a1 1 0 0 0-1-1z"/>
                      </svg>Courses</a>
                        <div class="sub-menu">
                            <ul>
                                <li><a href="#coursesSection">BCA</a></li>
                                <li><a href="#coursesSection">BBA</a></li>
                                <li><a href="#coursesSection">BTech CSE</a></li>
                                <li><a href="#coursesSection">BTech ECE</a></li>
                                <li><a href="#coursesSection">MCA</a></li>
                            </ul>
                        </div>
                    </li>
                    <li class="active"><a href=""><svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-textarea-resize" viewBox="0 0 16 16">
                        <path d="M0 4.5A2.5 2.5 0 0 1 2.5 2h11A2.5 2.5 0 0 1 16 4.5v7a2.5 2.5 0 0 1-2.5 2.5h-11A2.5 2.5 0 0 1 0 11.5zM2.5 3A1.5 1.5 0 0 0 1 4.5v7A1.5 1.5 0 0 0 2.5 13h11a1.5 1.5 0 0 0 1.5-1.5v-7A1.5 1.5 0 0 0 13.5 3zm10.854 4.646a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708l3-3a.5.5 0 0 1 .708 0m0 2.5a.5.5 0 0 1 0 .708l-.5.5a.5.5 0 0 1-.708-.708l.5-.5a.5.5 0 0 1 .708 0"/>
                      </svg>Application form</a>
                        <div class="sub-menu">
                            <ul>
                                <li><a href="Admission.jsp">Student Addmission</a></li>
                                <li><a href="Application.jsp?post=HOD">HOD Application</a></li>
                                <li><a href="Application.jsp?post=FACULTY">Faculty Application</a></li>
                            </ul>
                        </div>
                    </li>
                    <!-- <li class="active"><a href="">HOD/Faculty</a></li> -->
                    <li class="active"><a href="Login.jsp"><svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-box-arrow-in-right" viewBox="0 0 16 16">
                        <path fill-rule="evenodd" d="M6 3.5a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-2a.5.5 0 0 0-1 0v2A1.5 1.5 0 0 0 6.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-8A1.5 1.5 0 0 0 5 3.5v2a.5.5 0 0 0 1 0z"/>
                        <path fill-rule="evenodd" d="M11.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 1 0-.708.708L10.293 7.5H1.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708z"/>
                      </svg>Login</a></li>
                    <li  class="active"><a href="#sec-4"><svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-telephone" viewBox="0 0 16 16">
                        <path d="M3.654 1.328a.678.678 0 0 0-1.015-.063L1.605 2.3c-.483.484-.661 1.169-.45 1.77a17.6 17.6 0 0 0 4.168 6.608 17.6 17.6 0 0 0 6.608 4.168c.601.211 1.286.033 1.77-.45l1.034-1.034a.678.678 0 0 0-.063-1.015l-2.307-1.794a.68.68 0 0 0-.58-.122l-2.19.547a1.75 1.75 0 0 1-1.657-.459L5.482 8.062a1.75 1.75 0 0 1-.46-1.657l.548-2.19a.68.68 0 0 0-.122-.58zM1.884.511a1.745 1.745 0 0 1 2.612.163L6.29 2.98c.329.423.445.974.315 1.494l-.547 2.19a.68.68 0 0 0 .178.643l2.457 2.457a.68.68 0 0 0 .644.178l2.189-.547a1.75 1.75 0 0 1 1.494.315l2.306 1.794c.829.645.905 1.87.163 2.611l-1.034 1.034c-.74.74-1.846 1.065-2.877.702a18.6 18.6 0 0 1-7.01-4.42 18.6 18.6 0 0 1-4.42-7.009c-.362-1.03-.037-2.137.703-2.877z"/>
                      </svg>Contact Us</a>
                    </li> 
                </ul>
            </nav>
        </div>
    </header>
    <main>
        <div id="img">
            <h1>Welcome to Technova Institute</h1>
        </div>
        <div id="sec-1">
            <div id="paragraph"><h1 class="about">About Technova Institute</h1>
              <p>Technova Institute</b> campus has been established in the year 2003. This institute has aimed a good
                reputation by providing technical management education sincerely and hence the
                students of remote district areas have also come to a conclusion that quality
                teaching even in technical field is also available at their doorstep. We,
                are committed to maintain an academic environment where
                teaching learning is enjoyable, enough space is there for independent thinking
                and creative urge, scope is there to cultivate and sustain the vitality of
                campus social and cultural life with honor.It is known and acclaimed for its intense
                passion and zeal in the creation and dissemination of knowledge for the needs
                of an emerging India.</p>
            </div>
	           <div id="news" class="sub-sec">
			    <h1>Current News</h1>
			    <ul>
			        <li><i class="fa-solid fa-bullhorn"></i> Admissions Open for the Academic Year 2024-25 - Apply Now!</li>
			        <li><i class="fa-solid fa-graduation-cap"></i> Convocation Ceremony Scheduled for March 15, 2024 - Register Today.</li>
			        <li><i class="fa-solid fa-trophy"></i> College Secures 1st Position in National Coding Championship.</li>
			        <li><i class="fa-solid fa-calendar-days"></i> Annual Cultural Fest "Vibrance 2024" Announced - Join the Celebrations!</li>
			        <li><i class="fa-solid fa-microphone"></i> Seminar on Artificial Intelligence by Dr. John Doe - Feb 20, 2024.</li>
			        <li><i class="fa-solid fa-seedling"></i> Tree Plantation Drive on Campus - Volunteers Welcome!</li>
			        <li><i class="fa-solid fa-briefcase"></i> Internship Opportunities with Top Tech Companies - Apply by Feb 25.</li>
			        <li><i class="fa-solid fa-users"></i> Student Council Elections - Cast Your Vote on March 10, 2024.</li>
			        <li><i class="fa-solid fa-heart-pulse"></i> Free Health Checkup Camp for Students & Staff Feb 28, 2024.</li>
			        <li><i class="fa-solid fa-flask-vial"></i> Science Exhibition by the Department of Physics - March 5, 2024.</li>
			        <li><i class="fa-solid fa-music"></i> Music Club Auditions - Register Before March 3, 2024.</li>
			        <li><i class="fa-solid fa-volleyball-ball"></i> Inter-College Sports Meet - Registration Open Now.</li>
			        <li><i class="fa-solid fa-chalkboard-teacher"></i> Faculty Development Program on EdTech Tools - Feb 22, 2024.</li>
			        <li><i class="fa-solid fa-lightbulb"></i> Innovation Cell Launch Event- Ideas for a Better Future!</li>
			    </ul>
			</div>  
        </div>       
        <section id="coursesSection" class="carousel-container">
          <div class="arrow left" onclick="scrollCarousel('left')"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chevron-double-left" viewBox="0 0 16 16">
            <path fill-rule="evenodd" d="M8.354 1.646a.5.5 0 0 1 0 .708L2.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0"/>
            <path fill-rule="evenodd" d="M12.354 1.646a.5.5 0 0 1 0 .708L6.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0"/>
          </svg>  </div>
          <div class="carousel" id="carousel">
            <div class="card">
              <h3>BCA</h3>
              <p>
                Some of the subjects taught in the BCA course include: Digital Electronics, Business System and Application,Visual Basic, PC Software, Mathematics, English Language, Computer Architecture and Systems Software, 
    			Information Systems Analysis & Design, Programming with C, Data Structure.
              </p>
            </div>
            <div class="card">
              <h3>BBA</h3>
              <p>
                 Some of the subjects taught in the BBA course are: Principles of Management, English Language & Communication, 
			    Financial Accounting, Mathematics, Business Communication, Statistics, Financial Management, Marketing Management, 
			    Economics Management, Information Systems, Material Management, etc.
              </p>
            </div>
            <div class="card">
              <h3>BTech CSE</h3>
              <p>
                Key subjects in the B.Tech CSE course include: Data Structures and Algorithms, Operating Systems, 
			    Database Management Systems, Artificial Intelligence, Machine Learning,Deep Learning, Computer Networking, 
			    Programming Languages like Java and Python, and Cybersecurity Basics.
              </p>
            </div>
            <div class="card">
              <h3>BTech ECE</h3>
              <p>
                Key subjects in the B.Tech ECE course include: Analog and Digital Communication, Microprocessors and Micro controllers, 
			    Signal Processing, VLSI Design, Embedded Systems, Control Systems, Electromagnetics waves, Wireless Communication, 
			    Internet of Things (IoT), and Antenna Design.
              </p>
            </div>
            <div class="card">
              <h3>MCA</h3>
              <p>
                Core subjects in the MCA program include: Advanced Java, Advanced Python, Networking and Security, Cloud Computing, 
			    Data Analytics, Advanced Database Management Systems, Artificial Intelligence, Machine Learning, Data Science,
			    Software Engineering, and Cyber Security.
              </p>
            </div>
          </div>
          <div class="arrow right" onclick="scrollCarousel('right')"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chevron-double-right" viewBox="0 0 16 16">
            <path fill-rule="evenodd" d="M3.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L9.293 8 3.646 2.354a.5.5 0 0 1 0-.708"/>
            <path fill-rule="evenodd" d="M7.646 1.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1 0 .708l-6 6a.5.5 0 0 1-.708-.708L13.293 8 7.646 2.354a.5.5 0 0 1 0-.708"/>
          </svg></div>
        </section>

       <div id="sec-6">
         <div class="recognition">
		    <header><h2>Recognition</h2></header>
		    <ul>
		        <li><strong>AICTE</strong> Approved Courses</li>
		        <li><strong>ICRA</strong> has also assigned "EG2+ WB" (WB stands for "West Bengal" State) (pronounced E G Two Plus W B) grade at the state level to the institute</li>
		        <li>The grading committee of <strong>ICRA</strong> has assigned "EB3+ IN" (IN stands for "All-India") grade at the all-India level, in management education</li>
		        <li>Affiliated to <strong>(COLLEGE NAME)</strong></li>
		        <li>TNVI has been awarded <strong>Grade – A</strong> with score 3.25 by <strong>NAAC</strong>.</li>
		        <li><strong>NBA</strong> Accredited Courses</li>
		        <li>Affiliating University <strong>(COLLEGE NAME)</strong></li>
		    </ul>
		</div>
		<div class="student-benefits">
		    <header><h2>Student Benefits</h2></header>
		    <ul>
		        <li>Separate Boys and Girls Hostel Facility.</li>
		        <li>Separate Boys & Girls Common Room.</li>
		        <li>Medical Unit for free Health Checks up.</li>
		        <li>Free Vaccination Services.</li>
		        <li>Bus services for students</li>
		        <li>AC Canteen</li>
		        <li><strong>Mediclaim Policy for sum assured of Rs. 50,000 per student</strong></li>
		        <li>5 country Study Abroad Program</li>
		        <li>Scholarships</li>
		    </ul>
		</div>
		</div>
		<div id="sec-7">
		    <div class="placement-container">
		        <div class="placement-header">
		            <h2>Placements</h2>
		        </div>
		        <ul class="placement-list">
		            <li>Placement legacy of 30 years - all eligible students of all pass-out batches got 1 to 2 job offers on average</li>
		            <li>Placement of TNVI group continues till the last student is offered a job</li>
		            <li>In 2021, 3 students got 6 job offers, 10 students got 5 job offers, 63 students got 4 job offers, 152 students got 3 job offers, 290 students got 2 job offers, 463 students got at least 1 job offer.</li>
		        </ul>
		    </div>
		    <div class="Internship-container">
		        <div class="Internship-header">
		            <h2>Internships</h2>
		        </div>
		        <ul class="Internship-list">
		            <li>All students undergo at least 3 internships during his/her course duration</li>
		            <li>Few companies - NTPC Ltd., WBSETCL, N.F. Railway, Damodar Valley Corporation, Metal & Steel Factory Ishapore and so on.</li>
		            <li>Internship opportunities in all top reputed companies of the country and abroad.</li>
		        </ul>
		    </div>
		</div>
		<div id="sec-8">
		    <div class="laboratories-container">
		        <div class="laboratories-header">
		            <h2>Laboratories</h2>
		        </div>
		        <ul class="laboratories-list">
		            <li>Each department has their own Laboratories.</li>
		            <li>An innovation research center, named the IEDC Innovation Laboratory.</li>
		            <li>Special Research laboratories named (AI+ML) Lab, ANTENNA Lab, INNOVATION Lab, AR-VR Lab, Computer vision lab, Data Science Lab.</li>
		            <li>Extensive Language Laboratories.</li>
		        </ul>
		    </div>
		    <div class="D-library-container">
		        <div class="D-library-header">
		            <h2>24*7 Digital Library</h2>
		        </div>
		        <ul class="D-library-list">
		            <li>Open 24 hrs x 365 days in a year.</li>
		            <li>Journals: 20235 e-journals (National/International).</li>
		            <li>IIEEE 169 online society-sponsored journals, transactions and magazines.</li>
		            <li>EBSCO business database, provides 1000 business publications.</li>
		            <li>Big reading room and Multimedia PCs</li>
		            <li>Subscription to Internationally renowned Digital Libraries like IEEE, JGATE, DELNET etc.</li>
		        </ul>
		    </div>
		</div>
		<div id="sec-9">
		    <div class="infrastructure-container">
		        <div class="infrastructure-header">
		            <h2>Infrastructure</h2>
		        </div>
		        <ul class="infrastructure-list">
		            <li>Institute has 2.4Gbps Internet Facilities.</li>
		            <li>Optical fiber cables based network has been implemented for the speed enhancement of network.</li>
		            <li>Free Wi-Fi Campus.</li>
		            <li>More than 5000 computers available for students.</li>
		            <li>Separate Boys’ and Girls’ common rooms and along with well-equipped sports rooms with TT boards, carom boards, football, cricket, volleyball, mini-golf, table tennis, chess kits, yoga mats, and basic fitness accessories.</li>
		            <li>Use of alternate sources of energy and energy conservation measures; Solar energy, LED bulbs.</li>
		            <li>Plastic Free & Disabled Friendly campuses.</li>
		            <li>Well-developed waste management system for greater efficiency and sustainability.</li>
		        </ul>
		    </div>
		    <div class="studentChapter-container">
		        <div class="studentChapter-header">
		            <h2>Student Chapter</h2>
		        </div>
		        <ul class="studentChapter-list">
		            <li>ISHRAE Technova Institute student chapter.</li>
		            <li>Society of data science (S4DS) - Technova Institute student chapter.</li>
		            <li>A very active ACM student chapter.</li>
		            <li>IEEE Student branch, ASME TNVI Student Chapter & SAE-TNVI Collegiate Club.</li>
		            <li>Technova Institute - AIP Society of Physics Students (SPS) Chapter</li>
		            <li>Sports club, Dance club and Music club</li>
		        </ul>
		    </div>
</div>

    </main>
    <footer>
        <div id="footer1">
            <div id="footer-sec">
                <div id="sec-2">
                    <!-- <img src="https://p4.wallpaperbetter.com/wallpaper/445/589/314/map-continents-world-map-wallpaper-preview.jpg" alt="image"> -->
                    <div id="sub-sec">
                        <img src="https://img.freepik.com/premium-photo/university-college-graduate-campus-logo-design-inspiration-template-design-emblem-school_1029473-590941.jpg?w=996" alt="college Logo">
                        <h2>Technova Institute</h2>
                    </div>
                    <p>Technova Institute is a premier institution dedicated to providing quality education and fostering holistic development.Our experienced faculty, modern infrastructure, and vibrant campus culture create an environment conducive to learning, innovation, and personal growth. At Technova Institute, we aim to nurture future leaders equipped with the skills and knowledge to excel in a dynamic world.</p>
                    <hr style="color: black; height: 2px; margin-top: 5px; width: 100%;">
                </div>
                <div id="sec-3">
                    <h2>Quick Links</h2>
                    <ul style="width:fit-content;">
                        <li style="cursor: pointer;" onclick="window.location.href='https://www.antiragging.in/'">Anti-Ragging</li>
                        <li style="cursor: pointer;" onclick="window.location.href='Events.html'">Events</li>
                        <li style="cursor: pointer;" onclick="window.location.href='Admission.jsp">Admission</li>
                        <li style="cursor: pointer;" onclick="window.location.href='Login.jsp'">Login</li>
                        <li></li>
                    </ul>
                </div>
                
                <div id="sec-4">
                        <h2>Contact us</h2>
                           <div id="sub-sec-4">
                            <h3><svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-buildings" viewBox="0 0 16 16">
                                <path d="M14.763.075A.5.5 0 0 1 15 .5v15a.5.5 0 0 1-.5.5h-3a.5.5 0 0 1-.5-.5V14h-1v1.5a.5.5 0 0 1-.5.5h-9a.5.5 0 0 1-.5-.5V10a.5.5 0 0 1 .342-.474L6 7.64V4.5a.5.5 0 0 1 .276-.447l8-4a.5.5 0 0 1 .487.022M6 8.694 1 10.36V15h5zM7 15h2v-1.5a.5.5 0 0 1 .5-.5h2a.5.5 0 0 1 .5.5V15h2V1.309l-7 3.5z"/>
                                <path d="M2 11h1v1H2zm2 0h1v1H4zm-2 2h1v1H2zm2 0h1v1H4zm4-4h1v1H8zm2 0h1v1h-1zm-2 2h1v1H8zm2 0h1v1h-1zm2-2h1v1h-1zm0 2h1v1h-1zM8 7h1v1H8zm2 0h1v1h-1zm2 0h1v1h-1zM8 5h1v1H8zm2 0h1v1h-1zm2 0h1v1h-1zm0-2h1v1h-1z"/>
                              </svg>Campus and office</h3>
                            <p>EM-4/1, Salt Lake, Sector-V, Kolkata-700091, West Bengal, India</p>
                            <h4><svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-telephone-fill" viewBox="0 0 16 16">
                                <path fill-rule="evenodd" d="M1.885.511a1.745 1.745 0 0 1 2.61.163L6.29 2.98c.329.423.445.974.315 1.494l-.547 2.19a.68.68 0 0 0 .178.643l2.457 2.457a.68.68 0 0 0 .644.178l2.189-.547a1.75 1.75 0 0 1 1.494.315l2.306 1.794c.829.645.905 1.87.163 2.611l-1.034 1.034c-.74.74-1.846 1.065-2.877.702a18.6 18.6 0 0 1-7.01-4.42 18.6 18.6 0 0 1-4.42-7.009c-.362-1.03-.037-2.137.703-2.877z"/>
                              </svg>+91 123456789</h4>
                            <p><svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-envelope" viewBox="0 0 16 16">
                                <path d="M0 4a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2zm2-1a1 1 0 0 0-1 1v.217l7 4.2 7-4.2V4a1 1 0 0 0-1-1zm13 2.383-4.708 2.825L15 11.105zm-.034 6.876-5.64-3.471L8 9.583l-1.326-.795-5.64 3.47A1 1 0 0 0 2 13h12a1 1 0 0 0 .966-.741M1 11.105l4.708-2.897L1 5.383z"/>
                              </svg>in.technovainstitute@gmail.com</p>    
                           </div>
                </div>
            </div>
            <div><p class="copy">Copyrights  &copy; 2024 Technova Institute.</p></div> 
        </div>
    </footer>
    <script>
    const carousel = document.getElementById('carousel');

    function scrollCarousel(direction) {
      const scrollAmount = 300; 
      if (direction === 'left') {
        carousel.scrollBy({ left: -scrollAmount, behavior: 'smooth' });
      } else if (direction === 'right') {
        carousel.scrollBy({ left: scrollAmount, behavior: 'smooth' });
      }
    }</script>
    <script src="popup.js"></script>
</body>
</html>