import React from 'react'
//OnHover turn Blue
function SidebarOption({ active, text, Icon ,href}) {
    return (
        <a className="sidebarLink" href ={href}>
      <div className={`sidebarOption ${active && "sidebarOption--active"}`}>
        <Icon />
        <h2>{text}</h2>
      </div>
        </a>
    );
  }

export default SidebarOption
